package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.BasketItemDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.BasketItem;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.service.BasketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository repository;
    private final CustomerServiceImpl customerService;
    private final ProductServiceImpl productService;
    private final BasketItemServiceImpl basketItemService;

    public final int BASKET_STATUS_NONE = 0;
    public final int BASKET_STATUS_SALED = 1;

    /*
    1-) basket yok direkt ürünü ekle
    2-) Basket var
           Eklenen ürün sepette varmı
                1-) varsa quantity artır total amount artır
                2-) yoksa yeni ürünü ekle
     */

    @Transactional
    @Override
    public BasketDto addProductToBasket(BasketDto basketDto) {

        Basket basket = repository.findBasketByCustomer_CustomerIdAndStatusEquals(basketDto.getCustomer().getCustomerId(), BASKET_STATUS_NONE);
        if (basket != null) {
            // Basket Var Senaryosu
            return sepetVarUrunEkle(basket, basketDto);
        } else {
            // Basket yok senaryosu
            return sepetYokYeniSepetOlustur(basketDto);
        }
    }

    @Override
    public BasketDto getBasketByCustomerId(String customerId) {
        Customer customer = findCustomerById(customerId);
        Basket basket = repository.findBasketByCustomer_CustomerIdAndStatusEquals(Integer.parseInt(customerId), BASKET_STATUS_NONE);
       return toDto(basket);
    }
    @Override
    public void removeProductFromBasket(String basketItemId) {
        basketItemService.delete(Integer.parseInt(basketItemId));
    }

    private BasketDto sepetYokYeniSepetOlustur(BasketDto basketDto) {
        Basket basket = new Basket();
        Customer customer = findCustomerById(String.valueOf(basketDto.getCustomer().getCustomerId()));
        basket.setCustomer(customer);
        basket.setStatus(BASKET_STATUS_NONE);
        List<BasketItem> basketItemList = new ArrayList<>();

        for (BasketItemDto basketItemDto : basketDto.getBasketItemList()) {
            BasketItem basketItem = new BasketItem();
            basketItem.setCount(basketItemDto.getCount());
            Product product = findProductById(basketItemDto.getProduct().getProductId());
            basketItem.setProduct(product);
            basketItem.setBasketItemAmount(basketItem.getCount() * product.getPrice());
            basketItem.setBasket(basket);
            basketItemList.add(basketItem);

//          basketItemList.add(basketItem);
//          Yukarıdaki kod satırını neden çalıştırmadı?
        }
        basket.setBasketItemList(basketItemList);
        basket.setTotalAmount( basket.getBasketItemList().get(0).getCount() * basketItemList.get(0).getProduct().getPrice());

//        basket = repository.save(basket)
//        basket.setTotalAmount(calculateBasketAmount(basket.getBasketId()));


        basket = repository.save(basket);

        return toDto(basket);
    }

    private BasketDto sepetVarUrunEkle(Basket basket, BasketDto basketDto) {
        List<BasketItem> basketItemList = basket.getBasketItemList();
        // Aşağıdaki kod satırı yerine gelen listeyi for ile dönebilirdik fakat bunu yapmak yerine repository'e metod yazıp oradan var mı yok mu kontrolünü yapmak bestPractice.
        BasketItem basketItem = basketItemService.findBasketItemByBasketIdAndProductId(basket.getBasketId(), basketDto.getBasketItemList().get(0).getProduct().getProductId());

        if (basketItem == null) {
            System.out.println("Eklenen ürün sepette yok");
            BasketItem newBasketItem = new BasketItem();

            // Product product = basketItem.getProduct(); Hoca bunu yazdı yüksek ihtimalle yanlış var!
            Product product = findProductById(basketDto.getBasketItemList().get(0).getProduct().getProductId());
            newBasketItem.setProduct(product);
            newBasketItem.setCount(basketDto.getBasketItemList().get(0).getCount());
            newBasketItem.setBasketItemAmount(basketDto.getBasketItemList().get(0).getCount() * product.getPrice());
            newBasketItem.setBasket(basket);
//            newBasketItem = basketItemService.save(newBasketItem);
            basketItemList.add(newBasketItem);
            ;
        } else {
            System.out.println("Eklenen ürün sepette var");
            System.out.println("liste var mı " + basketDto.getBasketItemList());
            System.out.println("BasketİtemList boş mu" + basketDto.getBasketItemList().get(0).getProduct().getName());
            System.out.println("BasketItem : " + basketItem);
            // Eklenen ürün sepette var
            Product product = basketItem.getProduct();
            basketItem.setProduct(product);
            basketItem.setCount(basketDto.getBasketItemList().get(0).getCount());
            basketItem.setBasket(basket);
            basketItem.setBasketItemAmount(basketDto.getBasketItemList().get(0).getCount() * product.getPrice());

        }

        basket.setTotalAmount(calculateBasketAmount(basket.getBasketId()));
        basket.setBasketItemList(basketItemList);
        repository.save(basket);

        return toDto(basket);

    }

    // Bu metod sepette daha önceden ürün varsa çalışır
    private double calculateBasketAmount(int basketId) {
        Basket basket = repository.findBasketByBasketId(basketId);
        double totalAmount = 0.0;
        for (BasketItem basketItem : basket.getBasketItemList()) {
            totalAmount += basketItem.getBasketItemAmount();
        }
        return totalAmount;
    }

    /*
    Yukarıdaki metodda neden Basket basket almıyorda basketId alıyor?
    Çünkü BasketItem'ı DB'te güncelledim. Güncelledim Basket güncellendi ama bende güncel basket yok! Yani sepetVarUrunEkle() metoduna parametre olarak
    gönderilen basket güncel değil! Bunun güncelini almam gerekiyor! Sen BasketItem'i güncellediğin an zaten Repository Basket'ını da güncelliyor. Yani
    senin BasketRepository'den güncel basket'ı çekmen gerekiyor.
     */

    public Customer findCustomerById(String id) {
        return customerService.findCustomerEntity(id);
    }


    private Product findProductById(int productId) {
        return productService.getProductEntity(String.valueOf(productId));
    }


    // Response'tan önce çalışacak olan metod
    public BasketDto toDto(Basket basket) {
        return BasketDto.builder()
                .basketId(basket.getBasketId())
                .totalAmount(basket.getTotalAmount())
                .customer(customerService.toDto(basket.getCustomer()))
                .status(basket.getStatus())
                .basketItemList(basket.getBasketItemList().stream().map(basketItem -> basketItemService.toDto(basketItem)).collect(Collectors.toList()))
                .build();
    }

    // Repository'e gidicek metod
    public Basket toEntity(BasketDto basketDto) {
        Basket basket = new Basket();
        basket.setTotalAmount(basketDto.getTotalAmount());
        basket.setStatus(basketDto.getStatus());
        basket.setCustomer(customerService.findCustomerEntity(String.valueOf(basketDto.getCustomer().getCustomerId())));
        return basket;
    }

    public Basket findBasketEntity(String id) {
        return repository.findById(Integer.valueOf(id)).get();
    }


}

package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.ShopAdminDto;
import com.example.ecommerce.request.ProductRequest;
import com.example.ecommerce.request.ShopAdminRequest;
import com.example.ecommerce.response.ProductResponse;
import com.example.ecommerce.response.ShopAdminResponse;
import com.example.ecommerce.service.ShopAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shop-admins")
@RequiredArgsConstructor
public class ShopAdminController {
    private final ShopAdminService shopAdminService;

    @PostMapping
    public ShopAdminResponse save(@RequestBody ShopAdminRequest shopAdminRequest) {
        return toResponse(shopAdminService.save(shopAdminRequest.toDto()));
    }

    @PutMapping("/{id}")
    public ShopAdminResponse update(@PathVariable(value = "id") String id, @RequestBody ShopAdminRequest shopAdminRequest) {
        return toResponse(shopAdminService.update(shopAdminRequest.toDto(), id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") String id) {
        shopAdminService.delete(id);
        return "Silme İşlemi Başarılı";
    }

    @PostMapping("add-product")
    public ProductResponse saveProduct(@RequestBody ProductRequest productRequest) {
        return toResponse(shopAdminService.addProduct(productRequest.toDto()));
    }

//    @GetMapping("delete-product")
//    public ProductResponse deleteProduct() {}
    // YUKARIDAKİ METOD SONRA EKLENİCEK

    @GetMapping("all-products")
    public List<ProductResponse> getAllProducts(){
        return shopAdminService.getAllProducts()
                .stream()
                .map(productDto -> toResponse(productDto))
                .collect(Collectors.toList());
    }

    public ShopAdminResponse toResponse(ShopAdminDto shopAdminDto) {
        return ShopAdminResponse.builder()
                .id(shopAdminDto.getId())
                .name(shopAdminDto.getName())
                .surname(shopAdminDto.getSurname())
                .email(shopAdminDto.getEmail())
                .phoneNumber(shopAdminDto.getPhoneNumber())
                .address(shopAdminDto.getAddress())
                .password(shopAdminDto.getPassword())
                .shopName(shopAdminDto.getShopName())
                .code(200)
                .message("Successful")
                .build();
    }

    public ProductResponse toResponse(ProductDto productDto) {
        return ProductResponse.builder()
                .productId(productDto.getProductId())
                .name(productDto.getName())
                .stock(productDto.getStock())
                .price(productDto.getPrice())
                .categoryId(productDto.getCategoryId())
                .shopId(productDto.getShopId())
                .build();
    }
}

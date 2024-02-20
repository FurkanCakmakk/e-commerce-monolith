package com.example.ecommerce.request;

import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.BasketItemDto;
import com.example.ecommerce.dto.CustomerDto;
import com.example.ecommerce.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BasketRequest {
    private final int customerId;
    private final int productId;
    private final int count;
//    private final int basketId;

    /*
    Her bir basketRequest'ten maksimum bir tane ürün ekleyebilir. Bundan dolayı da biz her gelen basket
    Request'te bir tane basketItemDtoList oluşturarak onun içine atıyoruz. Sonra da get(0) diyerek çekiyoruz.
    İşte bundan dolayı da productId alıyoruz request'ten!
    Neden get(0) diyoruz? Çünkü biliyoruz ki tek seferde maksimum bir tane ürün göndericek. Zaten o ürünüde
    dto içerisindeki listeye biz atıyoruz ve biliyoruz ki dto'daki ;
    private final List<BasketItemDto> basketItemList;
    alanındaki listede sadece bir tane eleman var.

     */

    // Service'e gitmeden önce çalışacak olan metod;
    public BasketDto toDto(){
        List<BasketItemDto> dtoList = new ArrayList<>();
        BasketItemDto dto = BasketItemDto.builder().product(new ProductDto(productId)).build();
        dto.setCount(count);
        dtoList.add(dto);
        return BasketDto.builder()
                .customer(CustomerDto.builder().customerId(customerId).build())
                .basketItemList(dtoList)
                .build();
    }
}

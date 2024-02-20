package com.example.ecommerce.request;

import com.example.ecommerce.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private final String name;
    private final int stock;
    private final double price;
    private final int categoryId;
    private final int shopId;

    public ProductDto toDto(){
        return ProductDto.builder()
                .name(this.name)
                .stock(this.stock)
                .price(this.price)
                .categoryId(this.categoryId)
                .shopId(this.shopId)
                .build();
    }
}

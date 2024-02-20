package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketDto {
    private final int basketId;
    private final double totalAmount;
    private final int status;
    private final CustomerDto customer;
    private final List<BasketItemDto> basketItemList;

}

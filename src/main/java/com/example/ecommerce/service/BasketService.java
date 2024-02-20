package com.example.ecommerce.service;

import com.example.ecommerce.dto.BasketDto;

public interface BasketService {
    BasketDto addProductToBasket(BasketDto basketDto);

    BasketDto getBasketByCustomerId(String customerId );

    void removeProductFromBasket(String basketItemId);



}

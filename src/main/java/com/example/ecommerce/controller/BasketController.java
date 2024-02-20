package com.example.ecommerce.controller;

import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.request.BasketRequest;
import com.example.ecommerce.response.BasketResponse;
import com.example.ecommerce.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("baskets")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping
    public BasketResponse addProductToBasket(@RequestBody BasketRequest basketRequest){
        return toResponse(basketService.addProductToBasket(basketRequest.toDto()));
    }

    @GetMapping("/{customerId}")
    public BasketResponse getBasketByCustomerId(@PathVariable String customerId){
        return toResponse(basketService.getBasketByCustomerId(customerId));
    }

    @DeleteMapping("/{basketItemId}")
    public String delete(@PathVariable String basketItemId){
        basketService.removeProductFromBasket(basketItemId);
        return "Silme işlemi başarılı";
    }

    public BasketResponse toResponse(BasketDto basketDto) {
        return BasketResponse.builder()
                .basketId(basketDto.getBasketId())
                .totalAmount(basketDto.getTotalAmount())
                .customerId(basketDto.getCustomer().getCustomerId())
                .basketItemList(basketDto.getBasketItemList())
                .build();
    }
}

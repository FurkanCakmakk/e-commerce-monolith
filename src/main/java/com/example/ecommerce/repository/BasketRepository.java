package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository <Basket,Integer> {
    Basket findBasketByCustomer_CustomerIdAndStatusEquals(int customer , int status);

    Basket findBasketByBasketId(int basketId);



}

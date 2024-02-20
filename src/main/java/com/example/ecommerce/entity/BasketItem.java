package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int basketItemId;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "productId")
    private Product product;
    private int count;
    private double basketItemAmount;
    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;


}

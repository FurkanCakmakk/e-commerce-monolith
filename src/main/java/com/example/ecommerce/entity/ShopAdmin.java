package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shopAdminId;
    private String name;
    private String surname;
    private String email;
    private int phoneNumber;
    private String address;
    private String password;
    private String shopName;



}

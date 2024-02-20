package com.example.ecommerce.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopAdminResponse {
    private int code;
    private String message;
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final int phoneNumber;
    private final String address;
    private final String password;
    private final String shopName;

}

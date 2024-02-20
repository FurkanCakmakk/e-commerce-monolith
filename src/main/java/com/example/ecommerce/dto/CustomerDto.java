package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDto {
    private final int customerId;
    private final String name;
    private final String surname;
    private final String email;
    private final int phoneNumber;
    private final String address;
    private final String password;
    private final List<BasketDto> basketList;



}

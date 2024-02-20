package com.example.ecommerce.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperAdminDto {
    private final int superAdminId;
    private final String username;
    private final String password;
}

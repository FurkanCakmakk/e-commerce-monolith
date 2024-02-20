package com.example.ecommerce.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperAdminResponse {
    private final int superAdminId;
    private final String username;
    private final String password;
}

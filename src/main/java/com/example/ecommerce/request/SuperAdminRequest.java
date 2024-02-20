package com.example.ecommerce.request;

import com.example.ecommerce.dto.SuperAdminDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperAdminRequest {
    private final int superAdminId;
    private final String username;
    private final String password;

    public SuperAdminDto toDto(){
        return SuperAdminDto.builder()
                .superAdminId(this.superAdminId)
                .username(this.username)
                .password(this.password)
                .build();
    }
}

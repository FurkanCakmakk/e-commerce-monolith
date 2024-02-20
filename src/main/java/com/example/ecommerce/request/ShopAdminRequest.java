package com.example.ecommerce.request;

import com.example.ecommerce.dto.ShopAdminDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopAdminRequest {
    private String name;
    private String surname;
    private String email;
    private int phoneNumber;
    private String address;
    private String password;
    private final String shopName;

    public ShopAdminDto toDto(){
        return  ShopAdminDto.builder()
                .name(this.name)
                .surname(this.surname)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .password(this.password)
                .shopName(this.shopName)
                .build();
    }
}

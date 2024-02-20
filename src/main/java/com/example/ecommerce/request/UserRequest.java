package com.example.ecommerce.request;

import com.example.ecommerce.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private int phoneNumber;
    private String address;
    private String password;

    public UserDto toDto(){
        return  UserDto.builder()
                .name(this.name)
                .surname(this.surname)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .password(this.password)
                .build();
    }

}

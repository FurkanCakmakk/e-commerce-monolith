package com.example.ecommerce.request;

import com.example.ecommerce.dto.CustomerDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {
    @NotEmpty(message = "La gardaş ismini bizden mi gocunuyon")
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    @Email
    private String email;
    private int phoneNumber;
    private String address;
    private String password;

    public CustomerDto toDto(){
        return  CustomerDto.builder()
                .name(this.name)
                .surname(this.surname)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .address(this.address)
                .password(this.password)
                .build();
    }
}

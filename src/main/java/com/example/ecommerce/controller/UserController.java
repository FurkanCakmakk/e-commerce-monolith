package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.request.UserRequest;
import com.example.ecommerce.response.UserResponse;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponse save(@RequestBody UserRequest userRequest){
        return toResponse(service.save(userRequest.toDto()));
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable String id){
        return toResponse(service.getUserById(id));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable String id ,  @RequestBody UserRequest userRequest){
        return toResponse(service.update(id , userRequest.toDto()));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Silme işlemi başarılı";
    }


    public UserResponse toResponse(UserDto userDto){
        return UserResponse.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .password(userDto.getPassword())
                .id(userDto.getId())
                .phoneNumber(userDto.getPhoneNumber())
                .code(200)
                .message("Başarılı")
                .build();
    }
}

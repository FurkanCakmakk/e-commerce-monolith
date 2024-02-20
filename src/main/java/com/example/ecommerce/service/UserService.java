package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto getUserById(String id);
    UserDto update(String id , UserDto userDto);
    void delete(String id);
}

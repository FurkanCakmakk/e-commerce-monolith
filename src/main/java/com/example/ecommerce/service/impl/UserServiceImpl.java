package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserDto save(UserDto userDto) {
        User user = toEntity(userDto);
        user = repository.save(user);
        return toDto(user);
    }

    @Override
    public UserDto getUserById(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());
    }

    @Override
    public UserDto update(String id, UserDto userDto) {
        User user = repository.findById(Integer.valueOf(id)).get();
        user.setAddress(userDto.getAddress());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return toDto(user);
    }

    @Override
    public void delete(String id) {
        repository.delete(findUserEntity(id));
    }


    // Repository'e gitmeden önce çalışacak metod
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        return user;
    }


    // Response'a gitmeden önce çalışacak olan toDto
    public UserDto toDto(User  user) {
        return UserDto.builder()
                .id(user.getShopAdminId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .address(user.getAddress())
                .build();
    }

    public User findUserEntity(String id){
        User user =repository.findById(Integer.parseInt(id)).get();
        return user;
    }
}

package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.SuperAdminDto;
import com.example.ecommerce.request.CategoryRequest;
import com.example.ecommerce.request.SuperAdminRequest;
import com.example.ecommerce.response.CategoryResponse;
import com.example.ecommerce.response.SuperAdminResponse;
import com.example.ecommerce.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("super-admins")
@RequiredArgsConstructor
public class SuperAdminController {

    private  final SuperAdminService superAdminService;


    @PostMapping
    public SuperAdminResponse save(@RequestBody SuperAdminRequest superAdminRequest){
        return toResponse(superAdminService.save(superAdminRequest.toDto()));
    }

    @GetMapping("/{id}")
    public SuperAdminResponse getSuperAdmin(@PathVariable String id){
        return toResponse(superAdminService.get(id));
    }


    @PostMapping("add-category")
    public CategoryResponse saveCategory(@RequestBody CategoryRequest categoryRequest){
        return toResponse(superAdminService.saveCategory(categoryRequest.toDto()));
    }

    @GetMapping("categories")
    public List<CategoryResponse> getAllCategory(){
        return superAdminService.getAllCategory()
                .stream()
                .map(categoryDto -> toResponse(categoryDto))
                .collect(Collectors.toList());
    }




    public SuperAdminResponse toResponse(SuperAdminDto superAdminDto){
        return SuperAdminResponse.builder()
                .superAdminId(superAdminDto.getSuperAdminId())
                .username(superAdminDto.getUsername())
                .password(superAdminDto.getPassword())
                .build();
    }

    // Client'a gitmeden önce çalışacak metod
    public CategoryResponse toResponse(CategoryDto categoryDto) {
        return CategoryResponse.builder()
                .categoryId(categoryDto.getCategoryId())
                .name(categoryDto.getName())
                .code(200)
                .message("Başarılı")
                .build();
    }
}

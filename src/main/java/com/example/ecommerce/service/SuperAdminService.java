package com.example.ecommerce.service;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.SuperAdminDto;

import java.util.List;

public interface SuperAdminService {
    SuperAdminDto save(SuperAdminDto superAdminDto);
    SuperAdminDto get(String id);

    CategoryDto saveCategory(CategoryDto dto);

    List<CategoryDto> getAllCategory();
}

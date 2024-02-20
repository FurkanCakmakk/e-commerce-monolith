package com.example.ecommerce.service;


import com.example.ecommerce.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto getCategory(String id);
    List<CategoryDto> getAllCategory();
    CategoryDto update(CategoryDto categoryDto , String id);
    void delete(String id);

}

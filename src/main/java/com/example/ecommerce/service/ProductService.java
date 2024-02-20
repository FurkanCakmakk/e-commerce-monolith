package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto getProduct(String id);
    ProductDto update(ProductDto productDto, String id);
    void delete(String id);

    List<ProductDto> getAllProducts();

//    List<ProductDto> getProductsByCategoryId(String categoryId);

}

package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.ShopAdminDto;

import java.util.List;

public interface ShopAdminService {
    ShopAdminDto save(ShopAdminDto shopAdminDto);
    ShopAdminDto getShopAdmin(String id);
    ShopAdminDto update(ShopAdminDto shopAdminDto , String id);
    void delete(String id);
    List<ShopAdminDto> getAllShopAdmin();

    ProductDto addProduct(ProductDto dto);

    List<ProductDto> getAllProducts();
}

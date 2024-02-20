package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.ShopAdminDto;
import com.example.ecommerce.entity.ShopAdmin;
import com.example.ecommerce.repository.ShopAdminRepository;
import com.example.ecommerce.service.ShopAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopAdminServiceImpl implements ShopAdminService {

    private final ShopAdminRepository shopAdminRepository;
    private final ProductServiceImpl productService;


    @Override
    public ShopAdminDto save(ShopAdminDto shopAdminDto) {
        ShopAdmin shopAdmin = toEntity(shopAdminDto);
        shopAdmin = shopAdminRepository.save(shopAdmin);
        return toDto(shopAdmin);

    }

    @Override
    public ShopAdminDto getShopAdmin(String id) {
        ShopAdmin shopAdmin = shopAdminRepository.findById(Integer.parseInt(id)).get();
        return  toDto(shopAdmin);
    }

    @Override
    public ShopAdminDto update(ShopAdminDto shopAdminDto, String id) {
        ShopAdmin shopAdmin = shopAdminRepository.findById(Integer.parseInt(id)).get();
        shopAdmin.setName(shopAdminDto.getName());
        shopAdmin.setSurname(shopAdminDto.getSurname());
        shopAdmin.setEmail(shopAdminDto.getEmail());
        shopAdmin.setPhoneNumber(shopAdminDto.getPhoneNumber());
        shopAdmin.setAddress(shopAdminDto.getAddress());
        shopAdmin.setPassword(shopAdminDto.getPassword());
        shopAdmin.setShopName(shopAdminDto.getShopName());
        return toDto(shopAdmin);
    }

    @Override
    public void delete(String id) {
        ShopAdmin shopAdmin = shopAdminRepository.findById(Integer.parseInt(id)).get();
        shopAdminRepository.delete(shopAdmin);
    }

    @Override
    public List<ShopAdminDto> getAllShopAdmin() {
        return null;
    }

    @Override
    public ProductDto addProduct(ProductDto dto) {
        return productService.save(dto);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }


    // Repository'e gitmeden önce çalışacak metod
    public ShopAdmin toEntity(ShopAdminDto shopAdminDto) {
        ShopAdmin shopAdmin = new ShopAdmin();
        shopAdmin.setName(shopAdminDto.getName());
        shopAdmin.setSurname(shopAdminDto.getSurname());
        shopAdmin.setEmail(shopAdminDto.getEmail());
        shopAdmin.setPhoneNumber(shopAdminDto.getPhoneNumber());
        shopAdmin.setAddress(shopAdminDto.getAddress());
        shopAdmin.setPassword(shopAdminDto.getPassword());
        shopAdmin.setShopName(shopAdminDto.getShopName());
        return shopAdmin;
    }


    // Response'a gitmeden önce çalışacak olan toDto
    public ShopAdminDto toDto(ShopAdmin shopAdmin) {
        return ShopAdminDto.builder()
                .id(shopAdmin.getShopAdminId())
                .name(shopAdmin.getName())
                .surname(shopAdmin.getSurname())
                .email(shopAdmin.getEmail())
                .phoneNumber(shopAdmin.getPhoneNumber())
                .password(shopAdmin.getPassword())
                .address(shopAdmin.getAddress())
                .shopName(shopAdmin.getShopName())
                .build();
    }

    public ShopAdmin findShopAdminEntity(String id){
        ShopAdmin shopAdmin =shopAdminRepository.findById(Integer.parseInt(id)).get();
        return shopAdmin;
    }

}

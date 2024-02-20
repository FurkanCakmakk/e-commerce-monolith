package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.SuperAdminDto;
import com.example.ecommerce.entity.SuperAdmin;
import com.example.ecommerce.repository.SuperAdminRepository;
import com.example.ecommerce.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {
    private final SuperAdminRepository repository;

    private final CategoryServiceImpl categoryService;


    @Override
    public SuperAdminDto save(SuperAdminDto superAdminDto) {
        SuperAdmin superAdmin = toEntity(superAdminDto);
        superAdmin = repository.save(superAdmin);
        return  toDto(superAdmin);
    }

    @Override
    public SuperAdminDto get(String id) {
        return toDto(findSuperAdminEntity(id));
    }

    @Override
    public CategoryDto saveCategory(CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return  categoryService.getAllCategory();
    }


    // Respons'a gitmeden önce çalışan metod
    public SuperAdminDto toDto(SuperAdmin superAdmin) {
        return SuperAdminDto.builder()
                .superAdminId(superAdmin.getSuperAdminId())
                .username(superAdmin.getUsername())
                .password(superAdmin.getPassword())
                .build();
    }

    // Repository'a gitmeden önce çalışan metod
    public SuperAdmin toEntity(SuperAdminDto superAdminDto) {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setPassword(superAdminDto.getPassword());
        superAdmin.setUsername(superAdminDto.getUsername());
        return superAdmin;
    }

    public SuperAdmin findSuperAdminEntity(String id) {
        return repository.findById(Integer.parseInt(id)).get();
    }

}

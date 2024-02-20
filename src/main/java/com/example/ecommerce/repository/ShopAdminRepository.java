package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ShopAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAdminRepository extends JpaRepository <ShopAdmin, Integer> {
}

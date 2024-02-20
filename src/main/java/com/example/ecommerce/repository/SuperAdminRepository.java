package com.example.ecommerce.repository;

import com.example.ecommerce.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin , Integer> {
}

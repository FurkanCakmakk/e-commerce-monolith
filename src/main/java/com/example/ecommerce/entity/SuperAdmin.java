package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int superAdminId;
    private String username;
    private String password;

}

package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private final int categoryId;
    private final String name;
}

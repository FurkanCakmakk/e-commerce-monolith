package com.example.ecommerce.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryItemRequest {
    private final int categoryId;
    private final int shopId;
}


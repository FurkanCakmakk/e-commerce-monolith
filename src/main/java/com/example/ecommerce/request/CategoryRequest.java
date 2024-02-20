package com.example.ecommerce.request;


import com.example.ecommerce.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {
    private final String name;

    public CategoryRequest(@JsonProperty("name") String name){
        this.name=name;
    }

    public CategoryDto toDto(){
        return CategoryDto.builder()
                .name(this.name)
                .build();
    }

}

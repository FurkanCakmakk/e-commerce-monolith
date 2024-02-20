package com.example.ecommerce.service;

import com.example.ecommerce.dto.CustomerDto;

import java.util.List;

public interface  CustomerService {
    CustomerDto save(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto , String id);
    CustomerDto getCustomer(String id);
    List<CustomerDto> getAllCustomer();
    void delete(String id);
}

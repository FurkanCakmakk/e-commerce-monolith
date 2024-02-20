package com.example.ecommerce.controller;

import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.CustomerDto;
import com.example.ecommerce.request.CustomerRequest;
import com.example.ecommerce.response.BasketResponse;
import com.example.ecommerce.response.CustomerResponse;
import com.example.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{id}")
    public CustomerResponse getCustomerId(@PathVariable(value = "id") String id) {
        return toResponse(customerService.getCustomer(id));
    }


    @PostMapping
    public CustomerResponse save(@Valid @RequestBody CustomerRequest customerRequest) {
        return toResponse(customerService.save(customerRequest.toDto()));
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable(value = "id") String id, @RequestBody @Valid CustomerRequest customerRequest) {
        return toResponse(customerService.update(customerRequest.toDto(), id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") String id) {
        customerService.delete(id);
        return "Silme İşlemi Başarılı";
    }

/*    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return  new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND);
    }*/


    public CustomerResponse toResponse(CustomerDto customerDto) {
        return CustomerResponse.builder()
                .id(customerDto.getCustomerId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .address(customerDto.getAddress())
                .password(customerDto.getPassword())
                .code(200)
                .message("Successful")
                .build();
    }

    public BasketResponse toResponse(BasketDto basketDto) {
        return BasketResponse.builder()
                .customerId(basketDto.getCustomer().getCustomerId())
                .basketId(basketDto.getBasketId())
                .basketItemList(basketDto.getBasketItemList())
                .totalAmount(basketDto.getTotalAmount())
                .build();
    }


}

package com.luxclusifmiguel.challenge.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerAndProductDto {

    @NotNull
    @NotBlank
    private CustomerDto customerDto;

    @NotNull
    @NotBlank
    private ProductDto productDto;

    public CustomerAndProductDto(CustomerDto customer, ProductDto productDto) {
        this.customerDto = customer;
        this.productDto = productDto;
    }

    public void setCustomerDto(CustomerDto customer) {
        this.customerDto = customer;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    @Override
    public String toString() {
        return "CustomerAndProductDto{" +
                "customer=" + customerDto +
                ", product=" + productDto +
                '}';
    }
}

package com.luxclusifmiguel.challenge.backend.dto;

import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerAndProductDto {

    @NotNull
    @NotBlank
    private Customer customer;

    @NotNull
    @NotBlank
    private Product product;

    public CustomerAndProductDto(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "CustomerAndProductDto{" +
                "customer=" + customer +
                ", product=" + product +
                '}';
    }
}

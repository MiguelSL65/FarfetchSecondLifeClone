package com.luxclusifmiguel.challenge.backend.dto;

import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Wrapper class to receive info from frontend form
 */
public class CustomerAndProduct {

    @NotNull
    @NotBlank
    private Customer customer;

    @NotNull
    @NotBlank
    private Product product;

    public CustomerAndProduct() {
    }

    public CustomerAndProduct(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    /**
     * Gets the customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer
     *
     * @param customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets the product
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product
     *
     * @param product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "CustomerAndProduct{" +
                "customer=" + customer +
                ", product=" + product +
                '}';
    }
}

package com.luxclusifmiguel.challenge.backend.util;

import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;

public class CustomerAndProduct {

    private Customer customer;
    private Product product;

    public CustomerAndProduct(Customer customer, Product product) {
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
        return "CustomerAndProduct{" +
                "customer=" + customer +
                ", product=" + product +
                '}';
    }
}

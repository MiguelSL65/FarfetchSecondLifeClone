package com.luxclusifmiguel.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * The product model entity
 */
@Entity
public class Product extends AbstractModel {

    private String brand;

    @Column(name = "conditions")
    private String condition;
    private String size;

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    /**
     *  Gets the user associated with this product
     *
     * @return the user
     */
    public Customer getUser() {
        return customer;
    }

    /**
     *  Sets the user associated with this product
     *
     * @param user the user to set
     */
    public void setUser(Customer user) {
        this.customer = user;
    }

    /**
     *  Gets the product's brand
     *
     * @return product's brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     *  Sets the product's brand
     *
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *  Gets the condition of the product
     *
     * @return the condition of the product
     */
    public String getCondition() {
        return condition;
    }

    /**
     *  Sets the condition of the product
     *
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     *  Gets the product size
     *
     * @return product size
     */
    public String getSize() {
        return size;
    }

    /**
     *  Sets the product size
     *
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", condition='" + condition + '\'' +
                ", size='" + size + '\'' +
                ", user=" + customer +
                '}';
    }
}

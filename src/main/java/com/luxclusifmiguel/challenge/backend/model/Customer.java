package com.luxclusifmiguel.challenge.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  The user model entity
 */

@Entity
@Table(name = "customer")
public class Customer extends AbstractModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String country;

    @JsonIgnore
    @OneToMany(
            // propagate changes on customer entity to account entities
            cascade = {CascadeType.ALL},

            // remove products if unlinked from user (?)
            orphanRemoval = true,

            // foreign key on product table to establish the
            // many-to-one relationship
            mappedBy = "customer",

            // fetch products from database together with customer
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    /**
     *  Gets the first name of the user
     *
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *  Sets the user first name
     *
     * @param firstName the name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *  Gets the user last name
     *
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *  Sets the user last name
     *
     * @param lastName the name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *  Gets the user email
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     *  Sets the user email
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *  Get the user phone number
     *
     * @return user phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     *  Sets the user phone number
     *
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *  Gets the user's country
     *
     * @return user's country
     */
    public String getCountry() {
        return country;
    }

    /**
     *  Sets the user's country
     *
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *  Gets the user's products
     *
     * @return user's products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     *  Adds a new product to the customer
     *
     * @param product the product to add
     */
    public void addProduct(Product product) {
        products.add(product);
        product.setUser(this);
    }

    /**
     *  Removes a product from the customer
     *
     * @param product the product to remove
     */
    public void removeProduct(Product product) {
        products.remove(product);
        product.setUser(null);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {

        return "User{" +
                "name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", products=" + products +
                '}';
    }
}

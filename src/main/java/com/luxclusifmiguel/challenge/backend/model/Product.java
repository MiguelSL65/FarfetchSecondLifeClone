package com.luxclusifmiguel.challenge.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.cfg.ImprovedNamingStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(
            // propagate changes on customer entity to account entities
            cascade = {CascadeType.ALL},

            // remove products if unlinked from user (?)
            orphanRemoval = true,

            // foreign key on product table to establish the
            // many-to-one relationship
            mappedBy = "product",

            // fetch products from database together with customer
            fetch = FetchType.EAGER
    )
    private List<Image> images = new ArrayList<>();

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

    public List<Image> getImages() {
        return images;
    }

    /**
     * Adds an image
     *
     * @param image the image to add
     */
    public void addImage(Image image) {
        images.add(image);
        image.setProduct(this);
    }

    /**
     * Removes an image
     *
     * @param image the image to remove
     */
    public void removeImage(Image image) {
        images.remove(image);
        image.setProduct(null);
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
                ", customer=" + customer +
                ", images=" + images +
                '}';
    }
}

package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.exceptions.ImageNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Image;
import com.luxclusifmiguel.challenge.backend.model.Product;

import java.util.List;

/**
 *  Common interface for Product services - provides methods to manage products
 */
public interface ProductService {

    /**
     *  Gets the product with the given id
     *
     * @param id the product id
     * @return the product
     */
    Product get(Integer id);

    /**
     * Retrieves a list with all products
     *
     * @return the products list
     */
    List<Product> productsList();

    /**
     * Adds an image to a product
     *
     * @param productId the product id
     * @param image the image
     */
    Image addImage(Integer productId, Image image) throws ImageNotFoundException;

    /**
     * Removes an image from a product
     *
     * @param productId the product id
     * @param imageId the image id
     */
    void removeImage(Integer productId, Integer imageId) throws ImageNotFoundException;

}

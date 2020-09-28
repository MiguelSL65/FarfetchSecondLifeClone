package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.model.Product;

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

}

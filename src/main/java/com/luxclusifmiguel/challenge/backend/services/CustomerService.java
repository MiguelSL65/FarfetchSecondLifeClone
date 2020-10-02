package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.exceptions.AssociationExistsException;
import com.luxclusifmiguel.challenge.backend.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.model.Customer;

import java.util.List;

/**
 *  Common interface for customer services - provides methods to manage customers
 */
public interface CustomerService {

    /**
     *  Gets the customer with the given id
     *
     * @param id the customer id
     * @return the customer
     */
    Customer get(Integer id);

    /**
     *  Saves a customer
     *
     * @param user  the customer to save
     * @return the saved customer
     */
    Customer save(Customer user);

    /**
     *  Deletes a customer by id
     *
     * @param id the customer id
     */
    void delete(Integer id) throws UserNotFoundException, AssociationExistsException;

    /**
     *  Gets a list of customer
     *
     * @return the customer list
     */
    List<Customer> usersList();

    /**
     *  Adds a product to a customer
     *
     * @param id        the customer id
     * @param product   the product
     * @return the product added
     */
    Product addProduct(Integer id, Product product) throws UserNotFoundException;

    /**
     *  Removes a product from the customer
     *
     * @param id            the customer id
     * @param productId     the product id
     */
    void removeProduct(Integer id, Integer productId) throws UserNotFoundException, ProductNotFoundException;
}

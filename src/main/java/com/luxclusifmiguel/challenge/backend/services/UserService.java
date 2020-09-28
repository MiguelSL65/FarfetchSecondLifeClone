package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.exceptions.AssociationExistsException;
import com.luxclusifmiguel.challenge.backend.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.model.Customer;

import java.util.List;

/**
 *  Common interface for user services - provides methods to manage users
 */
public interface UserService {

    /**
     *  Gets the user with the given id
     *
     * @param id the user id
     * @return the user
     */
    Customer get(Integer id);

    /**
     *  Saves a user
     *
     * @param user  the user to save
     * @return the saved user
     */
    Customer save(Customer user);

    /**
     *  Deletes a user by id
     *
     * @param id the user id
     */
    void delete(Integer id) throws UserNotFoundException, AssociationExistsException;

    /**
     *  Gets a list of users
     *
     * @return the users list
     */
    List<Customer> usersList();

    /**
     *  Adds a product to a user
     *
     * @param id        the user id
     * @param product   the product
     * @return
     */
    Product addProduct(Integer id, Product product) throws UserNotFoundException;

    /**
     *  Removes a product from the customer
     *
     * @param id            the user id
     * @param productId     the product id
     */
    void removeProduct(Integer id, Integer productId) throws UserNotFoundException, ProductNotFoundException;
}

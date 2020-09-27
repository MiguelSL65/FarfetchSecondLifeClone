package com.luxclusifmiguel.challenge.services;


import com.luxclusifmiguel.challenge.exceptions.AssociationExistsException;
import com.luxclusifmiguel.challenge.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.model.Product;
import com.luxclusifmiguel.challenge.model.Customer;
import com.luxclusifmiguel.challenge.persistence.ProductDao;
import com.luxclusifmiguel.challenge.persistence.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * An {@link UserService} implementation
 */
@Service
public class UserServiceImpl implements UserService {

    private CustomerDao userDao;
    private ProductDao productDao;

    /**
     *  Sets the user data access object
     *
     * @param userDao the user DAO to set
     */
    @Autowired
    public void setUserDao(CustomerDao userDao) {
        this.userDao = userDao;
    }

    /**
     *  Sets the product data access object
     *
     * @param productDao the product DAO to set
     */
    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * @see UserService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return userDao.findById(id).get();
    }

    /**
     * @see UserService#save(Customer)
     */
    @Override
    public Customer save(Customer user) {
        return userDao.save(user);
    }

    /**
     * @see UserService#delete(Integer)
     */
    @Override
    public void delete(Integer id) throws UserNotFoundException, AssociationExistsException {

        Customer user = Optional.of(userDao.findById(id).get())
                .orElseThrow(UserNotFoundException::new);

        if (!user.getProducts().isEmpty()) {
            throw new AssociationExistsException();
        }

        userDao.deleteById(id);
    }

    /**
     * @see UserService#usersList()
     */
    @Override
    public List<Customer> usersList() {
        return userDao.findAll();
    }

    /**
     * @see UserService#addProduct(Integer, Product)
     */
    @Override
    public Product addProduct(Integer id, Product product) throws UserNotFoundException {

        Customer user = Optional.of(userDao.findById(id).get())
                .orElseThrow(UserNotFoundException::new);

        user.addProduct(product);
        userDao.save(user);

        return user.getProducts().get(user.getProducts().size() - 1);
    }

    /**
     * @see UserService#removeProduct(Integer, Integer)
     */
    @Override
    public void removeProduct(Integer id, Integer productId) throws UserNotFoundException,
            ProductNotFoundException {

        Customer user = Optional.of(userDao.findById(id).get())
                .orElseThrow(UserNotFoundException::new);

        Product product = Optional.of(productDao.findById(productId).get())
                .orElseThrow(ProductNotFoundException::new);

        if (!product.getUser().getId().equals(id)) {
            throw new ProductNotFoundException();
        }

        user.removeProduct(product);
        userDao.save(user);
    }
}

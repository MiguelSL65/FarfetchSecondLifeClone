package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.exceptions.AssociationExistsException;
import com.luxclusifmiguel.challenge.backend.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.persistence.ProductDao;
import com.luxclusifmiguel.challenge.backend.persistence.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * An {@link CustomerService} implementation
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;
    private ProductDao productDao;

    /**
     *  Sets the customer data access object
     *
     * @param customerDao the customer DAO to set
     */
    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
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
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return customerDao.findById(id).get();
    }

    /**
     * @see CustomerService#save(Customer)
     */
    @Override
    public Customer save(Customer user) {
        return customerDao.save(user);
    }

    /**
     * @see CustomerService#delete(Integer)
     */
    @Override
    public void delete(Integer id) throws UserNotFoundException, AssociationExistsException {

        Customer user = Optional.of(customerDao.findById(id).get())
                .orElseThrow(UserNotFoundException::new);

        if (!user.getProducts().isEmpty()) {
            throw new AssociationExistsException();
        }

        customerDao.deleteById(id);
    }

    /**
     * @see CustomerService#usersList()
     */
    @Override
    public List<Customer> usersList() {
        return customerDao.findAll();
    }

    /**
     * @see CustomerService#addProduct(Integer, Product)
     */
    @Override
    public Product addProduct(Integer id, Product product) throws UserNotFoundException {

        Customer user = Optional.of(customerDao.findById(id).get())
                .orElseThrow(UserNotFoundException::new);

        user.addProduct(product);
        customerDao.save(user);

        return user.getProducts().get(user.getProducts().size() - 1);
    }

    /**
     * @see CustomerService#removeProduct(Integer, Integer)
     */
    @Override
    public void removeProduct(Integer id, Integer productId) throws UserNotFoundException,
            ProductNotFoundException {

        Customer user = Optional.of(customerDao.findById(id).get())
                .orElseThrow(UserNotFoundException::new);

        Product product = Optional.of(productDao.findById(productId).get())
                .orElseThrow(ProductNotFoundException::new);

        if (!product.getUser().getId().equals(id)) {
            throw new ProductNotFoundException();
        }

        user.removeProduct(product);
        productDao.delete(product);
        customerDao.save(user);
    }
}

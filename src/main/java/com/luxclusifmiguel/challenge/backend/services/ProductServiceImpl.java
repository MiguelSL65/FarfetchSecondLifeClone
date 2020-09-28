package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.persistence.ProductDao;
import com.luxclusifmiguel.challenge.backend.persistence.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * An {@link ProductService} implementation
 */
@Service
public class ProductServiceImpl implements ProductService{

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
     * @see ProductService#get(Integer)
     */
    @Override
    public Product get(Integer id) {
        return productDao.findById(id).get();
    }
}

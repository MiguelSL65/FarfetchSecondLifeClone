package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.exceptions.ImageNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Image;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.persistence.ImageDao;
import com.luxclusifmiguel.challenge.backend.persistence.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * An {@link ProductService} implementation
 */
@Service
public class ProductServiceImpl implements ProductService{

    private ImageDao imageDao;
    private ProductDao productDao;

    /**
     *  Sets the image data access object
     *
     * @param imageDao the image DAO to set
     */
    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
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

    @Override
    public List<Product> productsList() {
        return productDao.findAll();
    }

    @Override
    public Image addImage(Integer productId, Image image) throws ImageNotFoundException {

        assert productId != null;
        Product product = productDao.findById(productId).get();

        product.addImage(image);
        productDao.save(product);

        return product.getImages().get(product.getImages().size() - 1);
    }

    @Override
    public void removeImage(Integer productId, Integer imageId) throws ImageNotFoundException {

        Product product = Optional.of(productDao.findById(productId).get())
                .orElseThrow(ImageNotFoundException::new);

        Image image = Optional.of(imageDao.findById(imageId).get())
                .orElseThrow(ImageNotFoundException::new);

        if (!image.getProduct().getId().equals(productId)) {
            throw new ImageNotFoundException();
        }

        product.removeImage(image);
        imageDao.deleteById(imageId);
        productDao.save(product);
    }
}

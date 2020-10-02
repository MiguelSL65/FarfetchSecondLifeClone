package com.luxclusifmiguel.challenge.backend.controller;

import com.luxclusifmiguel.challenge.backend.converters.*;
import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import com.luxclusifmiguel.challenge.backend.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.services.ProductService;
import com.luxclusifmiguel.challenge.backend.services.CustomerService;
import com.luxclusifmiguel.challenge.backend.dto.CustomerAndProduct;
import com.luxclusifmiguel.challenge.backend.util.SheetsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller responsible for {@link Product} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private CustomerService customerService;
    private ProductService productService;
    private ProductDtoToProduct productDtoToProduct;
    private ProductToProductDto productToProductDto;

    /***
     * Sets the converter for converting between Product DTO and Product model objects
     *
     * @param productDtoToProduct the product DTO to product converter to set
     */
    @Autowired
    public void setProductDtoToProduct(ProductDtoToProduct productDtoToProduct) {
        this.productDtoToProduct = productDtoToProduct;
    }

    /**
     *  Sets the converter for converting between product model objects and product DTO
     *
     * @param productToProductDto the product to product DTO converter to set
     */
    @Autowired
    public void setProductToProductDto(ProductToProductDto productToProductDto) {
        this.productToProductDto = productToProductDto;
    }

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Sets the product service
     *
     * @param productService the product service to set
     */
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    private List<ProductDto> listAllProducts() {

        return productService.productsList().stream()
                .map(product -> productToProductDto.convert(product))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a representation of the given user products
     *
     * @param cid the customer id
     * @return the response entity
     */
    @GetMapping("/all-products/{cid}")
    private List<ProductDto> listUserProducts(@PathVariable Integer cid) throws UserNotFoundException {

        Customer customer = customerService.get(cid);

        if (customer == null) {
            throw new UserNotFoundException();
        }

        return customer.getProducts()
                .stream()
                .map(product -> productToProductDto.convert(product))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a representation of the user product
     *
     * @param pid the product id
     * @return the response entity
     */
    @GetMapping("/{pid}")
    private ProductDto showUserProduct(@PathVariable Integer pid) throws ProductNotFoundException {

        Product product = productService.get(pid);

        if (product == null || product.getUser() == null) {
            throw new ProductNotFoundException();
        }

        return productToProductDto.convert(product);
    }

    /**
     * Adds a product
     *
     * @param cid the customer id
     * @param productDto the product DTO
     * @return the response entity
     */
    @PostMapping("/{uid}")
    private Product addProduct(@PathVariable Integer cid, @Valid @RequestBody ProductDto productDto)
            throws UserNotFoundException {

            return customerService.addProduct(cid, productDtoToProduct.convert(productDto));
    }

    /**
     * Adds a product and a customer received on the frontend form
     *
     * @param customerAndProduct the customer and product DTO
     * @return the customer and product added
     */
    @PostMapping("/add")
    private CustomerAndProduct addFormData
            (@RequestBody CustomerAndProduct customerAndProduct)
            throws UserNotFoundException, IOException, GeneralSecurityException {

            Customer customer = customerAndProduct.getCustomer();
            Product product = customerAndProduct.getProduct();

            customerService.save(customerAndProduct.getCustomer());
            customerService.addProduct(customer.getId(), product);

            // add to google sheets
            SheetsUtil.addToSheet(customer, product);

            return new CustomerAndProduct(customer, product);
        }

    /**
     * Removes a product
     *
     * @param cid the customer id
     * @param pid the product id
     */
    @GetMapping("/remove/{cid}/{pid}")
    private void removeProduct(@PathVariable Integer cid, @PathVariable Integer pid)
            throws UserNotFoundException, ProductNotFoundException {

            customerService.removeProduct(cid, pid);
    }
}

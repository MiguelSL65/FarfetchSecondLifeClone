package com.luxclusifmiguel.challenge.backend.controller;

import com.luxclusifmiguel.challenge.backend.converters.CustomerDtoToCustomer;
import com.luxclusifmiguel.challenge.backend.converters.CustomerToCustomerDto;
import com.luxclusifmiguel.challenge.backend.converters.ProductDtoToProduct;
import com.luxclusifmiguel.challenge.backend.converters.ProductToProductDto;
import com.luxclusifmiguel.challenge.backend.dto.CustomerDto;
import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import com.luxclusifmiguel.challenge.backend.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.services.ProductService;
import com.luxclusifmiguel.challenge.backend.services.UserService;
import com.luxclusifmiguel.challenge.backend.util.CustomerAndProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller responsible for {@link Product} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private UserService userService;
    private ProductService productService;
    private ProductDtoToProduct productDtoToProduct;
    private ProductToProductDto productToProductDto;
    private CustomerDtoToCustomer customerDtoToCustomer;
    private CustomerToCustomerDto customerToCustomerDto;

    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }

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
     * Sets the user service
     *
     * @param userService the user service to set
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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

    /**
     * Retrieves a representation of the given user products
     *
     * @param uid the user id
     * @return the response entity
     */
    @GetMapping("/{uid}/product")
    public ResponseEntity<List<ProductDto>> listUserProducts(@PathVariable Integer uid) {

        Customer customer = userService.get(uid);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ProductDto> productDtos = customer.getProducts()
                .stream()
                .map(product -> productToProductDto.convert(product))
                .collect(Collectors.toList());

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /**
     * Retrives a representation of the user product
     *
     * @param uid the user id
     * @param pid the product id
     * @return the response entity
     */
    @GetMapping("/{uid}/product/{pid}")
    public ResponseEntity<ProductDto> showUserProduct(@PathVariable Integer uid, @PathVariable Integer pid) {

        Product product = productService.get(pid);

        if (product == null || product.getUser() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!product.getUser().getId().equals(uid)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productToProductDto.convert(product), HttpStatus.OK);
    }

    /**
     * Adds a product
     *
     * @param uid the customer id
     * @param productDto the product DTO
     * @return the response entity
     */
    @PostMapping("/{uid}")
    public Product addProduct(@PathVariable Integer uid, @Valid @RequestBody ProductDto productDto)
            throws UserNotFoundException {

            return userService.addProduct(uid, productDtoToProduct.convert(productDto));
    }

    @PostMapping("/add")
    public CustomerAndProduct addFormData
            (@RequestBody CustomerAndProduct customerAndProduct)
            throws UserNotFoundException {

            Customer customer = customerAndProduct.getCustomer();
            Product product = customerAndProduct.getProduct();

            userService.save(customer);
            userService.addProduct(customer.getId(), product);

            return new CustomerAndProduct(customer, product);
        }

    /**
     * Removes a product
     *
     * @param uid the user id
     * @param pid the product id
     * @return the response entity
     */
    @GetMapping("/{uid}/products/{pid}/remove")
    public ResponseEntity<?> removeProduct(@PathVariable Integer uid, @PathVariable Integer pid) {

        try {

            userService.removeProduct(uid, pid);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

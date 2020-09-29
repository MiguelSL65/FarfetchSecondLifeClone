package com.luxclusifmiguel.challenge.backend.controller;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.luxclusifmiguel.challenge.backend.converters.CustomerDtoToCustomer;
import com.luxclusifmiguel.challenge.backend.converters.CustomerToCustomerDto;
import com.luxclusifmiguel.challenge.backend.converters.ProductDtoToProduct;
import com.luxclusifmiguel.challenge.backend.converters.ProductToProductDto;
import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import com.luxclusifmiguel.challenge.backend.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.services.ProductService;
import com.luxclusifmiguel.challenge.backend.services.UserService;
import com.luxclusifmiguel.challenge.backend.dto.CustomerAndProductDto;
import com.luxclusifmiguel.challenge.backend.util.SheetsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller responsible for {@link com.luxclusifmiguel.challenge.backend.model.Product} related CRUD operations
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

        com.luxclusifmiguel.challenge.backend.model.Product product = productService.get(pid);

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
    public com.luxclusifmiguel.challenge.backend.model.Product addProduct(@PathVariable Integer uid, @Valid @RequestBody ProductDto productDto)
            throws UserNotFoundException {

            return userService.addProduct(uid, productDtoToProduct.convert(productDto));
    }

    @PostMapping("/add")
    public CustomerAndProductDto addFormData
            (@RequestBody CustomerAndProductDto customerAndProductDto)
            throws UserNotFoundException, IOException, GeneralSecurityException {

            Customer customer = customerAndProductDto.getCustomer();
            Product product = customerAndProductDto.getProduct();

            userService.save(customer);
            userService.addProduct(customer.getId(), product);

            Sheets sheetsService = SheetsUtil.getSheetsService();

            ValueRange appendBody = new ValueRange()
                .setValues(Collections.singletonList(
                        Arrays.asList(customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                                customer.getCountry(), customer.getPhone(), product.getBrand(),
                                product.getCondition(), product.getSize())
                ));

            AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
                .append(SheetsUtil.SPREADSHEET_ID, "challenge", appendBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();

            return new CustomerAndProductDto(customer, product);
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

package com.luxclusifmiguel.challenge.controller;

import com.luxclusifmiguel.challenge.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.model.Product;
import com.luxclusifmiguel.challenge.model.Customer;
import com.luxclusifmiguel.challenge.services.ProductService;
import com.luxclusifmiguel.challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * REST controller responsible for {@link Product} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
    public ResponseEntity<List<Product>> listUserProducts(@PathVariable Integer uid) {

        Customer user = userService.get(uid);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Product> products = user.getProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{uid}/product/{pid}")
    public ResponseEntity<Product> showUserProduct(@PathVariable Integer uid, @PathVariable Integer pid) {

        Product product = productService.get(pid);

        if (product == null || product.getUser() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!product.getUser().getId().equals(uid)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/{uid}/product")
    public ResponseEntity<?> addProduct(@PathVariable Integer uid, @Validated @RequestBody Product product,
                                        BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || product.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            Product product1 = userService.addProduct(uid, product);

            UriComponents uriComponents = uriComponentsBuilder.path("/api/user/" + uid + "/product" +
                    product1.getId()).build();

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

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

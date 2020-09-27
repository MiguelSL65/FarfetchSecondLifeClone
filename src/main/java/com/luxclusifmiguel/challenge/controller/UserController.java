package com.luxclusifmiguel.challenge.controller;

import com.luxclusifmiguel.challenge.exceptions.AssociationExistsException;
import com.luxclusifmiguel.challenge.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.model.Customer;
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
 *  REST controller responsible for {@link Customer} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    /**
     * Sets the user service
     *
     * @param userService the user service to set
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> listUsers() {

        List<Customer> users = userService.usersList();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> showUser(@PathVariable Integer id) {

        Customer user = userService.get(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addUser(@Validated @RequestBody Customer user,
                                     BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || user.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer savedUser = userService.save(user);

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/user/" + savedUser.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteUser(@PathVariable Integer id) {

        try {

            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (AssociationExistsException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.luxclusifmiguel.challenge.backend.controller;

import com.luxclusifmiguel.challenge.backend.converters.CustomerDtoToCustomer;
import com.luxclusifmiguel.challenge.backend.converters.CustomerToCustomerDto;
import com.luxclusifmiguel.challenge.backend.dto.CustomerDto;
import com.luxclusifmiguel.challenge.backend.exceptions.AssociationExistsException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.services.UserService;
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
 *  REST controller responsible for {@link Customer} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private CustomerDtoToCustomer customerDtoToCustomer;
    private CustomerToCustomerDto customerToCustomerDto;

    /**
     *  Sets the converter for converting between customer DTO and customer model objects
     *
     * @param customerDtoToCustomer the customer DTO to customer converter to set
     */
    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    /**
     *  Sets the converter for converting between customer model objects and customer DTO
     *
     * @param customerToCustomerDto the customer to customer DTO converter to set
     */
    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
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
     * Retrieves a representation of the given users
     *
     * @return the response entity
     */
    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> listUsers() {

        List<CustomerDto> customerDtos = userService.usersList().stream()
                .map(customer -> customerToCustomerDto.convert(customer))
                .collect(Collectors.toList());

        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    /**
     * Retrieves a representation of the user
     *
     * @param id the user id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> showUser(@PathVariable Integer id) {

        Customer user = userService.get(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * adds a user
     *
     * @param customerDto the user DTO
     * @param bindingResult the binding result object
     * @param uriComponentsBuilder the uri components builder object
     * @return the response entity
     */
    @PostMapping("/")
    public ResponseEntity<?> addUser(@Valid @RequestBody CustomerDto customerDto,
                                     BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || customerDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer savedUser = userService.save(customerDtoToCustomer.convert(customerDto));

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/user/" + savedUser.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Deletes a user
     *
     * @param id the user id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteUser(@PathVariable Integer id) {

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

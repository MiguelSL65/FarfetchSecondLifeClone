package com.luxclusifmiguel.challenge.backend.controller;

import com.luxclusifmiguel.challenge.backend.converters.CustomerDtoToCustomer;
import com.luxclusifmiguel.challenge.backend.converters.CustomerToCustomerDto;
import com.luxclusifmiguel.challenge.backend.dto.CustomerDto;
import com.luxclusifmiguel.challenge.backend.exceptions.AssociationExistsException;
import com.luxclusifmiguel.challenge.backend.exceptions.UserNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  REST controller responsible for {@link Customer} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;
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
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves a representation of the given customers
     *
     * @return the list of customers
     */
    @GetMapping("/")
    private List<CustomerDto> listCustomers() {

        return customerService.usersList().stream()
                .map(customer -> customerToCustomerDto.convert(customer))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a representation of the customer
     *
     * @param id the customer id
     * @return the customer
     */
    @GetMapping("/{id}")
    private Customer showCustomer(@PathVariable Integer id) {

        return customerService.get(id);
    }

    /**
     * adds a customer
     *
     * @param customerDto the customer DTO
     * @return the customer
     */
    @PostMapping("/")
    private Customer addCustomer(@Valid @RequestBody CustomerDto customerDto) {

        return customerService.save(customerDtoToCustomer.convert(customerDto));
    }

    /**
     * Deletes a customer
     *
     * @param id the customer id
     */
    @DeleteMapping("/{id}")
    private void deleteCustomer(@PathVariable Integer id) throws AssociationExistsException, UserNotFoundException {

          customerService.delete(id);
    }

}

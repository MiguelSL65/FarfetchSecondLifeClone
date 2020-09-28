package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.CustomerDto;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * The {@link Converter} implementation, responsible for {@link CustomerDto} to {@link Customer} type conversion
 */
@Component
public class CustomerDtoToCustomer extends AbstractConverter<CustomerDto, Customer> {

    private UserService userService;

    /**
     * Sets the user service
     *
     * @param userService the service to set
     */
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Converts the customer DTO into a customer model object
     *
     * @param customerDto the customer DTO
     * @return the customer
     */
    @Override
    public Customer convert(CustomerDto customerDto) {

        Customer customer = (customerDto.getId() != null ? userService.get(customerDto.getId()) : new Customer());

        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setCountry(customerDto.getCountry());

        return customer;
    }
}

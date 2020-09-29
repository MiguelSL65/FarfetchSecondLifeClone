package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link com.luxclusifmiguel.challenge.backend.model.Customer} to {@link CustomerDto} type conversion
 */
@Component
public class CustomerToCustomerDto extends AbstractConverter<com.luxclusifmiguel.challenge.backend.model.Customer, CustomerDto> {

    /**
     * Converts the customer model object into a customer DTO
     *
     * @param customer the customer
     * @return the customer DTO
     */
    @Override
    public CustomerDto convert(com.luxclusifmiguel.challenge.backend.model.Customer customer) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhone(customer.getPhone());
        customerDto.setCountry(customer.getCountry());

        return customerDto;
    }
}

package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.CustomerAndProductDto;
import com.luxclusifmiguel.challenge.backend.dto.CustomerDto;
import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import com.luxclusifmiguel.challenge.backend.util.CustomerAndProduct;
import org.springframework.stereotype.Component;

@Component
public class CustProdToCustProdDto extends AbstractConverter<CustomerAndProduct, CustomerAndProductDto> {

    @Override
    public CustomerAndProductDto convert(CustomerAndProduct customerAndProduct) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerAndProduct.getCustomer().getId());
        customerDto.setFirstName(customerAndProduct.getCustomer().getFirstName());
        customerDto.setLastName(customerAndProduct.getCustomer().getLastName());
        customerDto.setEmail(customerAndProduct.getCustomer().getLastName());
        customerDto.setCountry(customerAndProduct.getCustomer().getCountry());
        customerDto.setPhone(customerAndProduct.getCustomer().getPhone());

        ProductDto productDto = new ProductDto();
        productDto.setId(customerAndProduct.getProduct().getId());
        productDto.setBrand(customerAndProduct.getProduct().getBrand());
        productDto.setCondition(customerAndProduct.getProduct().getCondition());
        productDto.setSize(customerAndProduct.getProduct().getSize());

        return new CustomerAndProductDto(customerDto, productDto);
    }
}

package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.CustomerAndProductDto;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.services.ProductService;
import com.luxclusifmiguel.challenge.backend.services.UserService;
import com.luxclusifmiguel.challenge.backend.util.CustomerAndProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustProdDtoToCustProd extends AbstractConverter<CustomerAndProductDto, CustomerAndProduct> {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomerAndProduct convert(CustomerAndProductDto customerAndProductDto) {

        Customer customer = (customerAndProductDto.getCustomerDto().getId() != null ?
                userService.get(customerAndProductDto.getCustomerDto().getId()) : new Customer());

        Product product = (customerAndProductDto.getProductDto().getId() != null ?
                productService.get(customerAndProductDto.getProductDto().getId()) : new Product());

        return new CustomerAndProduct(customer, product);
    }
}

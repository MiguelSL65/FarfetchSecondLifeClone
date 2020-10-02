package com.luxclusifmiguel.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxclusifmiguel.challenge.backend.controller.ProductController;
import com.luxclusifmiguel.challenge.backend.converters.ProductDtoToProduct;
import com.luxclusifmiguel.challenge.backend.converters.ProductToProductDto;
import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import com.luxclusifmiguel.challenge.backend.model.Customer;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.services.CustomerService;
import com.luxclusifmiguel.challenge.backend.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;

    @Mock
    private ProductDtoToProduct productDtoToProduct;

    @Mock
    private ProductToProductDto productToProductDto;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testListUserProducts() throws Exception {

        int fakeCustomerId = 888;
        Customer customer = new Customer();
        customer.setId(fakeCustomerId);

        int fakeProductId = 999;
        String fakeBrand = "AR-DIDAS";
        String fakeCondition = "Excelent";
        String fakeSize = "23XL";

        Product product = new Product();
        product.setId(fakeProductId);
        product.setBrand(fakeBrand);
        product.setCondition(fakeCondition);
        product.setSize(fakeSize);
        customer.addProduct(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(fakeProductId);
        productDto.setBrand(fakeBrand);
        productDto.setCondition(fakeCondition);
        productDto.setSize(fakeSize);

        when(customerService.get(fakeCustomerId)).thenReturn(customer);
        when(productToProductDto.convert(ArgumentMatchers.any(Product.class))).thenReturn(productDto);

        mockMvc.perform(get("http://localhost:8080/api/product/all-products/{cid}", fakeCustomerId))
                .andExpect(status().isOk());

        verify(customerService, times(1)).get(fakeCustomerId);
        verify(productToProductDto, times(1)).convert(product);
    }
}

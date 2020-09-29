package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import com.luxclusifmiguel.challenge.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link ProductDto} to {@link com.luxclusifmiguel.challenge.backend.model.Product} type conversion
 */
@Component
public class ProductDtoToProduct extends AbstractConverter<ProductDto, com.luxclusifmiguel.challenge.backend.model.Product> {

    private ProductService productService;

    /**
     * Sets the product service
     *
     * @param productService the service to set
     */
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Converts the product DTO into a product model object
     *
     * @param productDto the product dto
     * @return the product object model
     */
    @Override
    public com.luxclusifmiguel.challenge.backend.model.Product convert(ProductDto productDto) {

        com.luxclusifmiguel.challenge.backend.model.Product product = (productDto.getId() != null ? productService.get(productDto.getId()) : new com.luxclusifmiguel.challenge.backend.model.Product());

        product.setBrand(productDto.getBrand());
        product.setCondition(productDto.getCondition());
        product.setSize(productDto.getSize());

        return product;
    }
}

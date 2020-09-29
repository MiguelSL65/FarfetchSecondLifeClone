package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link com.luxclusifmiguel.challenge.backend.model.Product} to {@link ProductDto} type conversion
 */
@Component
public class ProductToProductDto extends AbstractConverter<com.luxclusifmiguel.challenge.backend.model.Product, ProductDto> {

    /**
     * Converts the product model object into a product DTO
     *
     * @param product the product
     * @return the product DTO
     */
    @Override
    public ProductDto convert(com.luxclusifmiguel.challenge.backend.model.Product product) {

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setBrand(product.getBrand());
        productDto.setCondition(product.getCondition());
        productDto.setSize(product.getSize());

        return productDto;
    }
}

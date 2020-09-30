package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.ProductDto;
import com.luxclusifmiguel.challenge.backend.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Product} to {@link ProductDto} type conversion
 */
@Component
public class ProductToProductDto extends AbstractConverter<Product, ProductDto> {

    /**
     * Converts the product model object into a product DTO
     *
     * @param product the product
     * @return the product DTO
     */
    @Override
    public ProductDto convert(Product product) {

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setBrand(product.getBrand());
        productDto.setCondition(product.getCondition());
        productDto.setSize(product.getSize());

        return productDto;
    }
}

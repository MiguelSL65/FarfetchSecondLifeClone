package com.luxclusifmiguel.challenge.backend.dto;

import com.luxclusifmiguel.challenge.backend.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The {@link com.luxclusifmiguel.challenge.backend.model.Product} data transfer object
 */
public class ProductDto {

    private Integer id;

    @NotNull(message = "Brand is mandatory")
    @NotBlank(message = "Brand is mandatory")
    @Size(min = 1, max = 64)
    private String brand;

    @NotNull(message = "Condition is mandatory")
    @NotBlank(message = "Condition is mandatory")
    @Size(min = 1, max = 64)
    private String condition;

    @NotNull(message = "Size is mandatory")
    @NotBlank(message = "Size is mandatory")
    private String size;

    /**
     * Gets the id of the product DTO
     *
     * @return the product DTO id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of the product DTO
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *  Gets the product's DTO brand
     *
     * @return product's DTO brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     *  Sets the product's DTO brand
     *
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *  Gets the condition of the product DTO
     *
     * @return the condition of the product DTO
     */
    public String getCondition() {
        return condition;
    }

    /**
     *  Sets the condition of the product DTO
     *
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     *  Gets the product DTO size
     *
     * @return product DTO size
     */
    public String getSize() {
        return size;
    }

    /**
     *  Sets the product DTO size
     *
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }
}

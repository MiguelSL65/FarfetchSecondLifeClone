package com.luxclusifmiguel.challenge.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 *  The image model entity
 */
@Entity
public class Image extends AbstractModel{

    private String fileName;
    private String fileType;

    @JsonIgnore
    @ManyToOne
    private Product product;

    public Image() {
    }

    public Image(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    /**
     *  Gets the image file name
     *
     * @return image file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *  Sets the image file name
     *
     * @param fileName the file name to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     *  Gets the image file type
     *
     * @return image file type
     */
    public String getFileType() {
        return fileType;
    }

    /**
     *  Sets the image file type
     *
     * @param fileType the file type to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Sets the product
     *
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Gets the product
     *
     * @return the product
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Image{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", product=" + product +
                '}';
    }
}

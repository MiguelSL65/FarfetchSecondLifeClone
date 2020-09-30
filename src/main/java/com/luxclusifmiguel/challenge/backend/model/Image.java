package com.luxclusifmiguel.challenge.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *  The image model entity
 */
@Entity
public class Image extends AbstractModel {

    private String fileName;
    private String fileType;

    @Lob
    private String imageUrl;

    @JsonIgnore
    @ManyToOne
    private Product product;

    public Image() {
    }

    public Image(String fileName, String fileType, String imageUrl) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.imageUrl = imageUrl;
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
     *  Gets the image data bytes
     *
     * @return image URL
     */
    public String getImageUrl() {
        return this.imageUrl;
    }

    /**
     *  Sets the image data bytes
     *
     * @param imageUrl the URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}

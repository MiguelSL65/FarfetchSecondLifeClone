package com.luxclusifmiguel.challenge.backend.dto;

import com.luxclusifmiguel.challenge.backend.model.Image;

import javax.validation.constraints.NotNull;

/**
 *  The {@link Image} data transfer object
 */
public class ImageDto {

    private Integer id;

    @NotNull(message = "File must have a name")
    private String fileName;

    @NotNull(message = "File must have a .type")
    private String fileType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the file name
     *
     * @return the name of the file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name
     *
     * @param fileName the file name to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the file type
     *
     * @return the file type
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the file type
     *
     * @param fileType the type to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "ImageDto{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}

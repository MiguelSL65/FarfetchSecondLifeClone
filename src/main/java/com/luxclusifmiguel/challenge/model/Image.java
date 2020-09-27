package com.luxclusifmiguel.challenge.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Arrays;

/**
 *  The image model entity
 */
@Entity
public class Image extends AbstractModel {

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    public Image() {
    }

    public Image(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
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
     * @return image bytes
     */
    public byte[] getData() {
        return data;
    }

    /**
     *  Sets the image data bytes
     *
     * @param data the bytes to set
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Image{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}

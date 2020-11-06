package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.exceptions.ImageNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Common interface for Image services - provides methods to manage images
 */
public interface ImageService {

    /**
     * Gets an image by id
     *
     * @param id the image id
     * @return the image
     */
    Image get(Integer id);

    /**
     * Saves a file locally
     *
     * @param file the file to save
     */
    void saveFile(MultipartFile file);

    /**
     * Returns a list with all images
     *
     * @return the images list
     */
    List<Image> getAllImages();

    /**
     * Saves an image to the DB and relates it to the last product
     * on product list.
     *
     * @param file the file to save
     */
    Image storeFile(MultipartFile file) throws IOException, ImageNotFoundException;
}

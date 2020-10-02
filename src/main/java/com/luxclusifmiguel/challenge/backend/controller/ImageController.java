package com.luxclusifmiguel.challenge.backend.controller;

import com.luxclusifmiguel.challenge.backend.converters.ImageDtoToImage;
import com.luxclusifmiguel.challenge.backend.converters.ImageToImageDto;
import com.luxclusifmiguel.challenge.backend.dto.ImageDto;
import com.luxclusifmiguel.challenge.backend.exceptions.ImageNotFoundException;
import com.luxclusifmiguel.challenge.backend.exceptions.ProductNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Image;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.services.ImageService;
import com.luxclusifmiguel.challenge.backend.services.ProductService;
import com.luxclusifmiguel.challenge.backend.util.GoogleDriveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller responsible for {@link Image} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/image")
public class ImageController {

    private ImageService imageService;
    private ProductService productService;
    private ImageToImageDto imageToImageDto;

    @Autowired
    public void setImageToImageDto(ImageToImageDto imageToImageDto) {
        this.imageToImageDto = imageToImageDto;
    }

    /**
     * Sets the image service
     *
     * @param imageService service to set
     */
    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

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
     * Adds an image to the database (Google Drive not working A.T.M.)
     * Uncomment {@link ImageService#saveFile(MultipartFile)} to store locally
     * check Path on the aforementioned method to suit local machine
     *
     * @param file the image received
     * @return the image
     */
    @PostMapping("/addfile")
    private MultipartFile addImage(@RequestBody MultipartFile file) throws IOException, ImageNotFoundException, GeneralSecurityException {

        //imageService.saveFile(file);
        imageService.storeFile(file);

        //GoogleDriveUtil.addToDrive(file);

        return file;
    }

    /**
     * Get an image by its id
     *
     * @param iid the image id
     * @return the image
     */
    @GetMapping("/{iid}")
    private ImageDto getImage(@PathVariable Integer iid) throws ImageNotFoundException {

        if (imageService.get(iid) == null) {
            throw new ImageNotFoundException();
        }

        return imageToImageDto.convert(imageService.get(iid));
    }

    /**
     * Retrieves a representation of all products images
     *
     * @param pid the product id
     * @return the list of images
     */
    @GetMapping("/list-images/{pid}")
    private List<ImageDto> listAllImages(@PathVariable Integer pid) throws ProductNotFoundException {

        Product product = productService.get(pid);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        return product.getImages().stream()
                .map(image -> imageToImageDto.convert(image))
                .collect(Collectors.toList());
    }

    /**
     * Removes an image
     *
     * @param pid the product id
     * @param iid the image id
     */
    @GetMapping("/remove/{pid}/{iid}")
    private void removeImage(@PathVariable Integer pid, @PathVariable Integer iid) throws ImageNotFoundException {

        productService.removeImage(pid, iid);
    }
}

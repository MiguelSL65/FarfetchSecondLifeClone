package com.luxclusifmiguel.challenge.backend.services;

import com.luxclusifmiguel.challenge.backend.exceptions.ImageNotFoundException;
import com.luxclusifmiguel.challenge.backend.model.Image;
import com.luxclusifmiguel.challenge.backend.model.Product;
import com.luxclusifmiguel.challenge.backend.persistence.ImageDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.SLF4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * An {@link ImageService} implementation
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private ImageDao imageDao;
    private ProductService productService;
    private Logger logger = LoggerFactory.getLogger(SLF4JLogger.DEFAULT_MESSAGE_FACTORY_CLASS);

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
     * Sets the image data access object
     *
     * @param imageDao the DAO to set
     */
    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    /**
     * @see ImageService#get(Integer)
     */
    @Override
    public Image get(Integer id) {
        return imageDao.findById(id).get();
    }

    /**
     * @see ImageService#getAllImages()
     */
    @Override
    public List<Image> getAllImages() {
        return imageDao.findAll();
    }

    /**
     * @see ImageService#storeFile(MultipartFile)
     */
    @Override
    public Image storeFile(MultipartFile file) throws ImageNotFoundException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Image image = new Image(fileName, file.getContentType());

        Product product = productService.get(productService.productsList().size());

        return productService.addImage(product.getId(), image);
    }

    /**
     * @see ImageService#saveFile(MultipartFile)
     */
    @Override
    public void saveFile(MultipartFile file) {

        String path = "C:\\Users\\Migue\\Documents\\uploads\\";

        File fileToConvert = new File(path + file.getOriginalFilename());

        try {
            fileToConvert.createNewFile();

            FileOutputStream output = new FileOutputStream(fileToConvert);
            output.write(file.getBytes());
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

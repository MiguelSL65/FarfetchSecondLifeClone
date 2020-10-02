package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.ImageDto;
import com.luxclusifmiguel.challenge.backend.model.Image;
import com.luxclusifmiguel.challenge.backend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link ImageDto} to {@link Image} type conversion
 */
@Component
public class ImageDtoToImage extends AbstractConverter<ImageDto, Image> {

    private ImageService imageService;

    /**
     * Sets the image service
     *
     * @param imageService the service to set
     */
    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Converts the Image DTO to an image model object
     *
     * @param imageDto the image DTO
     * @return the image model object
     */
    @Override
    public Image convert(ImageDto imageDto) {

        Image image = (imageDto.getId() != null ? imageService.get(imageDto.getId()) : new Image());

        image.setFileName(imageDto.getFileName());
        image.setFileType(imageDto.getFileType());
        //image.setProduct(imageDto.getProduct());
        return image;
    }
}

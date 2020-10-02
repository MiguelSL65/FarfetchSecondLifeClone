package com.luxclusifmiguel.challenge.backend.converters;

import com.luxclusifmiguel.challenge.backend.dto.ImageDto;
import com.luxclusifmiguel.challenge.backend.model.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Image} to {@link ImageDto} type conversion
 */
@Component
public class ImageToImageDto extends AbstractConverter<Image, ImageDto> {

    /**
     * Converts the image model object into an Image DTO
     *
     * @param image the image
     * @return the image DTO
     */
    @Override
    public ImageDto convert(Image image) {

        ImageDto imageDto = new ImageDto();

        imageDto.setId(image.getId());
        imageDto.setFileName(image.getFileName());
        imageDto.setFileType(image.getFileType());

        return imageDto;
    }
}

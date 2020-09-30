package com.luxclusifmiguel.challenge.backend.exceptions;

import com.luxclusifmiguel.challenge.backend.errors.ErrorMessage;

public class ImageNotFoundException extends ChallengeException {

    /**
     * @see Exception#Exception(String)
     */
    public ImageNotFoundException() {
        super(ErrorMessage.IMAGE_NOT_FOUN);
    }
}

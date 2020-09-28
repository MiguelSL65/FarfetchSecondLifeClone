package com.luxclusifmiguel.challenge.backend.exceptions;


import com.luxclusifmiguel.challenge.backend.errors.ErrorMessage;

public class ProductNotFoundException extends ChallengeException {

    public ProductNotFoundException() {
        super(ErrorMessage.PRODUCT_NOT_FOUND);
    }
}

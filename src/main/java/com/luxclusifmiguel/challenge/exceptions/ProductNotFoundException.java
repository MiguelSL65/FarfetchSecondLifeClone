package com.luxclusifmiguel.challenge.exceptions;


import com.luxclusifmiguel.challenge.errors.ErrorMessage;

public class ProductNotFoundException extends ChallengeException {

    public ProductNotFoundException() {
        super(ErrorMessage.PRODUCT_NOT_FOUND);
    }
}

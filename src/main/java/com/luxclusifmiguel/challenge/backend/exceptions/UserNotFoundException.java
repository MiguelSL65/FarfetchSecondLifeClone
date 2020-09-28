package com.luxclusifmiguel.challenge.backend.exceptions;

import com.luxclusifmiguel.challenge.backend.errors.ErrorMessage;

/**
 * Thrown to indicate that User was not found
 */
public class UserNotFoundException extends ChallengeException {

    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}

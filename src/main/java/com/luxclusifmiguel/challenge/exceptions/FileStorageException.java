package com.luxclusifmiguel.challenge.exceptions;

import com.luxclusifmiguel.challenge.errors.ErrorMessage;
import com.luxclusifmiguel.challenge.exceptions.ChallengeException;

public class FileStorageException extends ChallengeException {

    public FileStorageException(String message) {
        super(message);
    }
}

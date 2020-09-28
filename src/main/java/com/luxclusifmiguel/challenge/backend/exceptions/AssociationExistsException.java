package com.luxclusifmiguel.challenge.backend.exceptions;

import com.luxclusifmiguel.challenge.backend.errors.ErrorMessage;

public class AssociationExistsException extends ChallengeException {

    public AssociationExistsException() {
        super(ErrorMessage.ASSOCIATION_EXISTS);
    }
}

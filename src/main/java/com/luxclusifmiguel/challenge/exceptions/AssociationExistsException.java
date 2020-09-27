package com.luxclusifmiguel.challenge.exceptions;

import com.luxclusifmiguel.challenge.errors.ErrorMessage;

public class AssociationExistsException extends ChallengeException {

    public AssociationExistsException() {
        super(ErrorMessage.ASSOCIATION_EXISTS);
    }
}

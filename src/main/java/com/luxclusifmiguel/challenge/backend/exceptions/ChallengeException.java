package com.luxclusifmiguel.challenge.backend.exceptions;

/**
 *  A generic Challenge Exception to be used as base for concrete types of exceptions
 *
 * @see Exception
 */
public class ChallengeException extends Exception {

    /**
     * @see Exception#Exception(String)
     */
    public ChallengeException(String message) {
        super(message);
    }
}

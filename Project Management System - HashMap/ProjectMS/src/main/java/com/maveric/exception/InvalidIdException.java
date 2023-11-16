package com.maveric.exception;

public class InvalidIdException extends Exception {
    public InvalidIdException(String invalidId) {
        super("EXCEPTION!! - "+invalidId);
    }
}

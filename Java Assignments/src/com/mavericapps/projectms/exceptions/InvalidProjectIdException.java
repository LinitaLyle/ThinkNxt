package com.mavericapps.projectms.exceptions;

public class InvalidProjectIdException extends Exception {
    public InvalidProjectIdException(String msg) {
        super("EXCEPTION!! - "+msg);
    }
}

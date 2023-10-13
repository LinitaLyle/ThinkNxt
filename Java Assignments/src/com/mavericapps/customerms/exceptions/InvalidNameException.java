package com.mavericapps.customerms.exceptions;

public class InvalidNameException extends Throwable {
    public InvalidNameException(String message) {
        super("EXCEPTION!!! "+message);
    }
}

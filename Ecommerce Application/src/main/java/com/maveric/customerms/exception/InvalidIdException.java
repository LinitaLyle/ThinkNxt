package com.maveric.customerms.exception;

public class InvalidIdException extends Throwable {
    public InvalidIdException(String msg) {
        super("EXCEPTION!!! - "+msg);
    }
}

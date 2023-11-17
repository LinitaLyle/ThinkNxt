package com.maveric.orderms.exceptions;

public class InvalidProductNameException extends Throwable {
    public InvalidProductNameException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

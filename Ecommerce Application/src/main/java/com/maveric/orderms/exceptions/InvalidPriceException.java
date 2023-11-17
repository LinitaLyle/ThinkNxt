package com.maveric.orderms.exceptions;

public class InvalidPriceException extends Throwable {
    public InvalidPriceException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

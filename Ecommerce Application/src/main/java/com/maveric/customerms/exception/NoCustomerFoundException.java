package com.maveric.customerms.exception;

public class NoCustomerFoundException extends Throwable {
    public NoCustomerFoundException(String s) {
        super("EXCEPTION!!! - "+s);
    }
}

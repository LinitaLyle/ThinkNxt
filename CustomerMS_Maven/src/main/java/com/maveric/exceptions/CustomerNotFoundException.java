package com.maveric.exceptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String s) {
        super("EXCEPTION!!!"+s);
    }
}

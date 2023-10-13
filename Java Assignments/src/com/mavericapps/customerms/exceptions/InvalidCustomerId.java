package com.mavericapps.customerms.exceptions;

public class InvalidCustomerId extends Exception {
    public InvalidCustomerId(String s) {
        super ("EXCEPTION!!! "+s);
    }
}

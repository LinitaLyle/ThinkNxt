package com.maveric.exceptions;

public class InvalidCustomerId extends Exception {
    public InvalidCustomerId(String s) {
        super ("EXCEPTION!!! "+s);
    }
}

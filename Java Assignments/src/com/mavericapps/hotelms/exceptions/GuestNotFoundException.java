package com.mavericapps.hotelms.exceptions;

public class GuestNotFoundException extends Exception {
    public GuestNotFoundException(String message) {
        super("EXCEPTION!!! - "+ message);
    }
}

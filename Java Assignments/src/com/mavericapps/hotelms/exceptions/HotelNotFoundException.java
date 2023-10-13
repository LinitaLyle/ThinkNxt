package com.mavericapps.hotelms.exceptions;

public class HotelNotFoundException extends Exception {
    public HotelNotFoundException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

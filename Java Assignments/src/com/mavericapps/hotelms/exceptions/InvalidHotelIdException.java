package com.mavericapps.hotelms.exceptions;

public class InvalidHotelIdException extends Exception {
    public InvalidHotelIdException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

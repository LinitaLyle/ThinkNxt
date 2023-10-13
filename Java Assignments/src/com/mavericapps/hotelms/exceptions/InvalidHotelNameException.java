package com.mavericapps.hotelms.exceptions;

public class InvalidHotelNameException extends Exception {
    public InvalidHotelNameException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

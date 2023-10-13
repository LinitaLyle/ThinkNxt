package com.mavericapps.hotelms.exceptions;

public class RoomNotFoundException extends Exception {
    public RoomNotFoundException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

package com.mavericapps.hotelms.exceptions;

public class InvalidRoomNoException extends Exception {
    public InvalidRoomNoException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

package com.mavericapps.hotelms.exceptions;

public class CannotAddRoomException extends Exception {
    public CannotAddRoomException(String msg) {
        super("EXCEPTION!!! - "+ msg);
    }
}

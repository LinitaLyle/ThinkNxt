package com.mavericapps.hotelms.exceptions;

public class InvalidGuestIdException extends Exception {
    public InvalidGuestIdException(String guestIdIsInvalid)
    {
        super("EXCEPTION!!! - "+ guestIdIsInvalid);
    }
}

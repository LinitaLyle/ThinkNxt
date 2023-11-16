package com.maveric.exception;

public class InvalidProjectNameException extends Exception{
    InvalidProjectNameException(String msg)
    {
        super("EXCEPTION!! - "+msg);
    }
}

package com.maveric.customerms.exception;

public class InvalidNameException extends Exception{
    public InvalidNameException(String msg)
    {
        super("EXCEPTION!!! - "+msg);
    }
}

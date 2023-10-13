package com.mavericapps.projectms.exceptions;

public class InvalidNameException extends Exception{
    public InvalidNameException(String msg)
    {
        super("EXCEPTION!!! - "+msg);
    }
}

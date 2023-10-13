package com.mavericapps.projectms.exceptions;

public class InvalidEmployeeIdException extends Exception{
    public InvalidEmployeeIdException(String msg)
    {
        super("EXCEPTION!!! - "+msg);
    }
}

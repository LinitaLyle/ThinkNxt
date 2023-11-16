package com.maveric.projectms.exception;

public class NoEmployeeFoundException extends Exception {
    public NoEmployeeFoundException(String s) {
        super("EXCEPTION!!!" +s);
    }
}

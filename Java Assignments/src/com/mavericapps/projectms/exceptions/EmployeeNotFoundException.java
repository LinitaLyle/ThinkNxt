package com.mavericapps.projectms.exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String s) {
        super("EXCEPTION!! "+s);

    }
}

package com.mavericapps.customerms.exceptions;

public class NoEmployeesRegisteredException extends Throwable {
    public NoEmployeesRegisteredException(String msg) {
        super("EXCEPTION!! "+msg);
    }
}

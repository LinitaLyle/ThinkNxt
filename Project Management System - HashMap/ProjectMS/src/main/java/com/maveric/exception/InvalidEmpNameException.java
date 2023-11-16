package com.maveric.exception;

public class InvalidEmpNameException extends Exception {
    public InvalidEmpNameException(String nameCannotBeNull) {
        super("EXCEPTION!! -"+nameCannotBeNull);
    }
}

package com.maveric.projectms.exception;

public class InvalidEmpNameException extends Exception {
    public InvalidEmpNameException(String nameCannotBeNull) {
        super("EXCEPTION!! -"+nameCannotBeNull);
    }
}

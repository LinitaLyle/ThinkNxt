package com.mavericapps.projectms.exceptions;

public class ProjectNameNullException extends Exception {
    public ProjectNameNullException(String s) {
        super("EXCEPTION!! - "+s);
    }
}

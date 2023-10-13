package com.mavericapps.projectms.exceptions;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(String s) {
        super("EXCEPTION!! "+s);
    }
}

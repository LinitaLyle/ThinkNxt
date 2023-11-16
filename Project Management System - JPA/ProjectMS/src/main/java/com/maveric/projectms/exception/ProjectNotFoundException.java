package com.maveric.projectms.exception;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(String str) {
        super("EXCEPTION!! - "+str);
    }
}

package com.maveric.orderms.exceptions;

public class NoOrderFoundException extends Throwable {
    public NoOrderFoundException(String s) {
        super("EXCEPTION!! - "+ s);
    }
}

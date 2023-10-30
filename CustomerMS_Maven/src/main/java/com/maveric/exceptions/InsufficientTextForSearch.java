package com.maveric.exceptions;

public class InsufficientTextForSearch extends Exception {
    public InsufficientTextForSearch(String msg) {
        super("EXCEPTION!!! "+msg);
    }
}

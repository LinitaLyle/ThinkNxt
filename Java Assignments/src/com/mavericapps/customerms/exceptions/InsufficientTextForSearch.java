package com.mavericapps.customerms.exceptions;

public class InsufficientTextForSearch extends Exception {
    public InsufficientTextForSearch(String msg) {
        super("EXCEPTION!!! "+msg);
    }
}

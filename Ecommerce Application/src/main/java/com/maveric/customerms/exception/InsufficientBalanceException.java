package com.maveric.customerms.exception;

public class InsufficientBalanceException extends Throwable {
    public InsufficientBalanceException(String msg) {
        super("EXCEPTION!!! - "+msg);
    }
}

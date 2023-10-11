package com.mavericapps.feedback_application;

public class Customer {
    private int custId;
    private String name;

    public Customer(int custId, String name) {
        this.custId = custId;
        this.name = name;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public String toString() {
        return "Customer{custId=" + this.custId + ", name='" + this.name + "'}";
    }

    public int getCustId() {
        return this.custId;
    }
}

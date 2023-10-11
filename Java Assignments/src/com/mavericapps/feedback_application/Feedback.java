package com.mavericapps.feedback_application;

public class Feedback {
    private int feedbackId;
    private String description;
    Customer customer;

    public Feedback(int feedbackId, String description, Customer customer) {
        this.feedbackId = feedbackId;
        this.description = description;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String toString() {
        return "Feedback{feedbackId=" + this.feedbackId + ", description='" + this.description + "', customer=" + this.customer + "}";
    }

}



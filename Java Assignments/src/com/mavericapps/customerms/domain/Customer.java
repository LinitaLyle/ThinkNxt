package com.mavericapps.customerms.domain;

public class Customer implements Comparable<Customer> {
    Integer id;
    String firstName;
    String lastName;

    public Customer(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public int compareTo(Customer custObj) {
        return this.id-custObj.getId();
    }

    @Override
    public String toString() {
        return "Customer:" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}

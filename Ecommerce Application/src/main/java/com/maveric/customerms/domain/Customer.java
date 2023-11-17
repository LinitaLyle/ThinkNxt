package com.maveric.customerms.domain;

import com.maveric.orderms.domain.CreatedOrder;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private long id;
    @Column(name="customer_name", nullable = false)
    private String name;
    @Column(name="balance", nullable = false)
    private double balance;
    @OneToMany(mappedBy = "createdBy")
    private Set<CreatedOrder> createdOrders;
    public Customer() {}

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Set<CreatedOrder> getCreatedOrders() {
        return createdOrders;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCreatedOrders(Set<CreatedOrder> createdOrders) {
        this.createdOrders = createdOrders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

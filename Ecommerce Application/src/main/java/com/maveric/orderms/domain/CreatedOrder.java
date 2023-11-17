package com.maveric.orderms.domain;

import com.maveric.customerms.domain.Customer;
import jakarta.persistence.*;

@Entity
@Table(name="created_order")
public class CreatedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer createdBy;
    @Column(name="creation_date_time_ms")
    private long  creationDateTimeMillis;
    @Column(name="amount", nullable = false)
    private double amount;
    @Column(name="product_info", nullable = false)
    private String productInfo;

    public CreatedOrder() {}

    public CreatedOrder(Customer customer, String productDesc, double productPrice) {
        this.createdBy = customer;
        this.productInfo = productDesc;
        this.amount = productPrice;
    }

    @Override
    public String toString() {
        return "CreatedOrder{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", amount=" + amount +
                ", productInfo='" + productInfo + '\'' +
                '}';
    }
}

package com.maveric.customerms.dao;

import com.maveric.customerms.domain.Customer;

import java.util.Optional;

public interface ICustomerDAO {
    Customer save(Customer customer);

    Optional<Customer> findById(long id);
}

package com.mavericapps.customerms.service;

import com.mavericapps.customerms.domain.Customer;
import com.mavericapps.customerms.exceptions.CustomerNotFoundException;
import com.mavericapps.customerms.exceptions.InsufficientTextForSearch;
import com.mavericapps.customerms.exceptions.InvalidCustomerId;
import com.mavericapps.customerms.exceptions.InvalidNameException;

import java.util.List;

public interface ICustomerService {
    Customer register(String firstName, String lastName) throws InvalidNameException;
    Customer findById(int id) throws InvalidCustomerId, CustomerNotFoundException;
    List<Customer> findCustomersByFirstNameAscendingId(String firstName) throws InvalidNameException, InsufficientTextForSearch, CustomerNotFoundException;
}

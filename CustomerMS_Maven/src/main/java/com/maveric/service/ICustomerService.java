package com.maveric.service;

import com.maveric.domain.Customer;
import com.maveric.exceptions.CustomerNotFoundException;
import com.maveric.exceptions.InsufficientTextForSearch;
import com.maveric.exceptions.InvalidCustomerId;
import com.maveric.exceptions.InvalidNameException;

import java.util.List;

public interface ICustomerService {
    Customer register(String firstName, String lastName) throws InvalidNameException;
    Customer findById(int id) throws InvalidCustomerId, CustomerNotFoundException;
    List<Customer> findCustomersByFirstNameAscendingId(String firstName) throws InvalidNameException, InsufficientTextForSearch, CustomerNotFoundException;
}

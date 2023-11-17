package com.maveric.customerms.service;

import com.maveric.customerms.domain.Customer;
import com.maveric.customerms.exception.InsufficientBalanceException;
import com.maveric.customerms.exception.InvalidIdException;
import com.maveric.customerms.exception.NoCustomerFoundException;

import javax.naming.InvalidNameException;

public interface ICustomerService {
    Customer findById(long id) throws InvalidIdException, NoCustomerFoundException;

    void register(String name, double balance) throws InvalidNameException, InsufficientBalanceException;
}

package com.maveric.customerms.service;

import com.maveric.customerms.dao.CustomerDAO;
import com.maveric.customerms.domain.Customer;
import com.maveric.customerms.exception.InsufficientBalanceException;
import com.maveric.customerms.exception.InvalidIdException;
import com.maveric.customerms.exception.NoCustomerFoundException;
import com.maveric.customerms.utilities.Utility;

import javax.naming.InvalidNameException;
import java.util.Optional;

public class CustomerService implements ICustomerService {

    private CustomerDAO custDAO = new CustomerDAO();

    @Override
    public void register(String name, double balance) throws InvalidNameException, InsufficientBalanceException {
        Utility.validateString(name, 2, 15, "Invalid customer name");
        Utility.validateBalance(balance, "Customer does not have sufficient balance");
        Customer customer = new Customer(name, balance);
        custDAO.save(customer);
    }

    @Override
    public Customer findById(long id) throws InvalidIdException, NoCustomerFoundException {
        Utility.validateId(id, "Invalid Customer Id");
        Optional<Customer> cust = custDAO.findById(id);
        if (cust.isPresent()) return cust.get();
        throw new NoCustomerFoundException("Customer with id " + id + " not found");
    }

}


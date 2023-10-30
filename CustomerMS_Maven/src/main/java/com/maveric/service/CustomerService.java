package com.maveric.service;

import com.maveric.domain.Customer;
import com.maveric.exceptions.CustomerNotFoundException;
import com.maveric.exceptions.InsufficientTextForSearch;
import com.maveric.exceptions.InvalidCustomerId;
import com.maveric.exceptions.InvalidNameException;

import java.util.*;

public class CustomerService implements ICustomerService {

    private int generatedCustomerId;
    private Map<Integer, Customer> customerMap = new HashMap<>();

    @Override
    public Customer register(String firstName, String lastName) throws InvalidNameException {
        validateString(firstName);
        validateString(lastName);
        int custId = generateCustomerId();
        Customer customer = new Customer(custId, firstName, lastName);
        customerMap.put(custId, customer);
        return customer;
    }

    @Override
    public Customer findById(int id) throws InvalidCustomerId, CustomerNotFoundException {
        validateId(id);
        Customer customer = customerMap.get(id);
        if (customer == null) throw new CustomerNotFoundException("Customer with id " + id + " not found!!");
        return customer;
    }

    @Override
    public List<Customer> findCustomersByFirstNameAscendingId(String searchStr) throws InvalidNameException, InsufficientTextForSearch, CustomerNotFoundException {
        if (searchStr == null || searchStr.length() < 3)
            throw new InsufficientTextForSearch("Insufficient text for search!!!");
        List<Customer> resCustomers = new ArrayList<>();
        Collection<Customer> customers = customerMap.values();

        for (Customer customer : customers) {
            if (customer.getFirstName().startsWith(searchStr)) resCustomers.add(customer);
        }
        if (resCustomers.isEmpty()) throw new CustomerNotFoundException("No Customers found!!");
        Collections.sort(resCustomers);
        return resCustomers;
    }

    private int generateCustomerId() {
        return ++this.generatedCustomerId;
    }

    private void validateString(String strValue) throws InvalidNameException {
        if (strValue == null) throw new InvalidNameException("Name cannot be null");
        if (strValue.isBlank() || strValue.isEmpty()) throw new InvalidNameException("Name cannot be empty");
        if (strValue.length() < 2 || strValue.length() > 10)
            throw new InvalidNameException("Name not in acceptable size");
        for (int i = 0; i < strValue.length(); i++) {
            if (!Character.isLetter(strValue.charAt(i))) throw new InvalidNameException("Invalid characters in name");
        }
    }

    private void validateId(int id) throws InvalidCustomerId {
        if (id < 1) throw new InvalidCustomerId("Customer id " + id + " is invalid...");
    }

    public Map<Integer, Customer> getCustomerMap() {
        return customerMap;
    }


}

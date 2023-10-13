package com.mavericapps.customerms.service;

import com.mavericapps.customerms.domain.Customer;
import com.mavericapps.customerms.exceptions.CustomerNotFoundException;
import com.mavericapps.customerms.exceptions.InsufficientTextForSearch;
import com.mavericapps.customerms.exceptions.InvalidCustomerId;
import com.mavericapps.customerms.exceptions.InvalidNameException;

import java.util.*;

public class CustomerService implements ICustomerService{

    private int generatedCustomerId;
    private Map<Integer,Customer> customerMap = new HashMap<>();

    @Override
    public Customer register(String firstName, String lastName) throws InvalidNameException {
        System.out.println("Registering customer \""+firstName+"\" \""+lastName+"\"");
        validateString(firstName);
        validateString(lastName);
        int custId = generateCustomerId();
        Customer customer = new Customer(custId,firstName,lastName);
        customerMap.put(custId,customer);
        return customer;
    }

    @Override
    public Customer findById(int id) throws InvalidCustomerId, CustomerNotFoundException {
        validateId(id);
        Customer customer = null;
        for (Map.Entry<Integer, Customer> entry : customerMap.entrySet())
        {
            if(entry.getKey()==id)
            {
                customer = entry.getValue();
                break;
            }
        }
        if(customer==null)
            throw new CustomerNotFoundException("Customer with id "+id+" not found!!" );
        return customer;
    }

    @Override
    public List<Customer> findCustomersByFirstNameAscendingId(String searchStr) throws InvalidNameException, InsufficientTextForSearch, CustomerNotFoundException {
        if(searchStr.length()<3)
            throw new InsufficientTextForSearch("Insufficient text for search!!!");
        List<Customer> resCustomers = new ArrayList<>();
        Collection<Customer> customers = customerMap.values();
        //System.out.println(customers);

        for(Customer customer: customers)
            if(customer.getFirstName().startsWith(searchStr))
                resCustomers.add(customer);

        if(resCustomers.isEmpty())
            throw new CustomerNotFoundException("No Customers found!!");

        Collections.sort(resCustomers);
        return resCustomers;
    }

    private int generateCustomerId()
    {
        return ++this.generatedCustomerId;
    }

    private void validateString(String strValue) throws InvalidNameException {
        if(strValue.isBlank()||strValue.isEmpty())
            throw new InvalidNameException("Name cannot be empty");
        if(strValue.length() < 2|| strValue.length()>10)
            throw new InvalidNameException("Name not in acceptable size");
        for(int i=0;i<strValue.length();i++)
            if(!Character.isLetter(strValue.charAt(i)))
                throw new InvalidNameException("Invalid characters in name");

    }

    private void validateId(int id) throws InvalidCustomerId {
        if(id<1)
            throw new InvalidCustomerId("Customer id "+id+" is invalid...");
    }

    public void displayCustomers()
    {
        for(Map.Entry<Integer,Customer> customerEntry : customerMap.entrySet())
        {
            System.out.println(customerEntry.getValue());
        }
    }
}

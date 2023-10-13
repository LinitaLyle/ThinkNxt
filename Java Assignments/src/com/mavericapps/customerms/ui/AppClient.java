package com.mavericapps.customerms.ui;

import com.mavericapps.customerms.domain.Customer;
import com.mavericapps.customerms.exceptions.CustomerNotFoundException;
import com.mavericapps.customerms.exceptions.InsufficientTextForSearch;
import com.mavericapps.customerms.exceptions.InvalidCustomerId;
import com.mavericapps.customerms.exceptions.InvalidNameException;
import com.mavericapps.customerms.service.CustomerService;

import java.util.List;

public class AppClient {

    public CustomerService cusSer = new CustomerService();

    public static void main(String[] args) {
        AppClient appClient = new AppClient();
        appClient.runApp();
    }

    public void runApp() {
        System.out.println("************* Register Customer *************");
        registerCustomer("Linita", "Lyle");
        registerCustomer("Bhavitha", "Reddy");
        registerCustomer("Samuel", "Prem");
        registerCustomer("Komala", "Komal");
        registerCustomer("Sammy", "Daniel");
        registerCustomer("", "Brown");//Throws Exception
        registerCustomer("Brown", ""); //Throws Exception
        displayCustomers();

        System.out.println("\n************* Find Customer by Id *************");
        int customerId = 2;
        findCustomerById(customerId);
        findCustomerById(0); // throws exception;
        System.out.println("\n************* Find Customer Starting with... *************");
        customerStartingWith("Sam");
        customerStartingWith("ab");//Throws exception
        customerStartingWith("abc");//No customer found

    }

    public void registerCustomer(String firstName, String lastName) {
        try {
            Customer resObj = cusSer.register(firstName, lastName);
            System.out.println("Details of customer registered: " + resObj);
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findCustomerById(int custId) {

        try {
            Customer resObj = cusSer.findById(custId);
            System.out.println("Details of customer with id " + custId + " is " + resObj);
        } catch (InvalidCustomerId | CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayCustomers() {
        System.out.println("\nDetails of all registered customers....");
        cusSer.displayCustomers();
    }

    public void customerStartingWith(String str) {
        try {
            List<Customer> allCustomers = cusSer.findCustomersByFirstNameAscendingId(str);
            System.out.println("Details of customers whose name starts with " + str);
            System.out.println(allCustomers);
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientTextForSearch e) {
            System.out.println(e.getMessage());
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

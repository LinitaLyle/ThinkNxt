package com.maveric.ui;

import com.maveric.domain.Customer;
import com.maveric.exceptions.CustomerNotFoundException;
import com.maveric.exceptions.InsufficientTextForSearch;
import com.maveric.exceptions.InvalidCustomerId;
import com.maveric.exceptions.InvalidNameException;
import com.maveric.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class AppClient {

    public final CustomerService cusSer = new CustomerService();
    private static final Logger Log = LogManager.getLogger(AppClient.class);

    public static void main(String[] args) {
        AppClient appClient = new AppClient();
        appClient.runApp();
    }

    public void runApp() {
        Log.info("************* Register Customer *************");
        registerCustomer("Linita", "Lyle");
        registerCustomer("Bhavitha", "Reddy");
        registerCustomer("Samuel", "Prem");
        registerCustomer("Komala", "Komal");
        registerCustomer("Sammy", "Daniel");
        registerCustomer("", "Brown");//Throws Exception
        registerCustomer("Brown", ""); //Throws Exception
        displayCustomers();

        Log.info("************* Find Customer by Id *************");
        int customerId = 2;
        findCustomerById(customerId);
        findCustomerById(0); // throws exception;
        Log.info("************* Find Customer Starting with... *************");
        customerStartingWith("Sam");
        customerStartingWith("ab");//Throws exception
        customerStartingWith("abc");//No customer found

    }

    public void registerCustomer(String firstName, String lastName) {
        try {
            Customer resObj = cusSer.register(firstName, lastName);
            Log.debug("Details of customer registered: "+ resObj);
        } catch (InvalidNameException e) {
            Log.error(e.getMessage());
        }
    }

    public void findCustomerById(int custId) {

        try {
            Customer resObj = cusSer.findById(custId);
            Log.debug("Details of customer with id " + custId + " is " + resObj);
        } catch (InvalidCustomerId | CustomerNotFoundException e) {
            Log.error(e.getMessage());
        }
    }

    public void displayCustomers() {
        Log.info("Details of all registered customers....");
        Map<Integer, Customer> customerMap=cusSer.getCustomerMap();
        for(Map.Entry<Integer,Customer> entry : customerMap.entrySet())
            Log.debug(entry.getValue());

    }

    public void customerStartingWith(String str) {
        try {
            List<Customer> allCustomers = cusSer.findCustomersByFirstNameAscendingId(str);
            Log.info("Details of customers whose name starts with "+ str);
            Log.debug(allCustomers);
        } catch (InvalidNameException | InsufficientTextForSearch | CustomerNotFoundException e) {
            Log.error(e.getMessage());
        }
    }
}

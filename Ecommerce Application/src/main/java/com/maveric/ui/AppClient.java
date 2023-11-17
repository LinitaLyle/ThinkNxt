package com.maveric.ui;

import com.maveric.customerms.domain.Customer;
import com.maveric.customerms.exception.InsufficientBalanceException;
import com.maveric.customerms.exception.InvalidIdException;
import com.maveric.customerms.exception.NoCustomerFoundException;
import com.maveric.customerms.service.CustomerService;
import com.maveric.orderms.domain.CreatedOrder;
import com.maveric.orderms.exceptions.InvalidPriceException;
import com.maveric.orderms.exceptions.InvalidProductNameException;
import com.maveric.orderms.exceptions.NoOrderFoundException;
import com.maveric.orderms.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InvalidNameException;

public class AppClient {

    private final CustomerService custSer = new CustomerService();
    private final OrderService orderSer = new OrderService(custSer);
    private static final Logger Log = LogManager.getLogger(AppClient.class);

    public static void main(String[] args) {
        AppClient appClient = new AppClient();
        appClient.runApp();
    }

    private void runApp()  {

        System.out.println("*********** REGISTER CUSTOMER **************");
        registerCustomer("Jane", 6000);
        registerCustomer("Jasmine", 9000);
        registerCustomer("Julianne", 16000);
        registerCustomer("Jerinne", 26000);
        registerCustomer("Sharon", 7800);
        registerCustomer("Salomi", 4000); // Insufficient balance
        registerCustomer("Si ta", 6000); // Invalid Name

          System.out.println("*********** FIND CUSTOMER BY ID **************");
           findCustomerById(1);
           findCustomerById(-1); // Invalid Id exception
           findCustomerById(20); // Customer not found exception

            System.out.println("*********** CREATE ORDER **************");

            createOrder(1, "Samsung Galaxy Note",25000.0);
            createOrder(2, "Realme Narzo Pro",15000.0);
            createOrder(3, "Iphone 11",100000.0);
            createOrder(1, "HP Laptop",60000.0);
            createOrder(-1, "Dell XPS 13", 10000.0); // Invalid id exception
            createOrder(4,"",9000); //Invalid product description exception
            createOrder(5,"Dell XPS 13", 0); // Invalid price;

            System.out.println("*********** FIND ORDER BY ID **************");
            findOrderById(1);
            findOrderById(2);
            findOrderById(-1); //invalid order id
            findCustomerById(8); // order not found

   }

   public void registerCustomer(String name, double balance)
    {
        Customer cust = null;
        try {
            custSer.register(name,balance);
            System.out.println("Customer registration successful - "+name);
        } catch (InvalidNameException e) {
            Log.info(e);
        } catch (InsufficientBalanceException e) {
            Log.info(e);
        }

    }

    public void findCustomerById(long id) {

        try {
            Customer cust = custSer.findById(id);
            System.out.println("Details of customer - "+cust);
        } catch (InvalidIdException | NoCustomerFoundException e) {
            Log.info(e.getMessage());
        }

    }

    public void createOrder(long custId, String prodDesc, double price)
    {
        try {
            CreatedOrder order = orderSer.createOrder(custId, prodDesc,price);
            System.out.println("Order created - "+order);
        } catch (InvalidIdException | InvalidProductNameException | InvalidPriceException | NoCustomerFoundException e) {
            Log.info(e.getMessage());
        }
    }

    public void findOrderById(long pId)
    {
        try {
            CreatedOrder order = orderSer.findById(pId);
            System.out.println("Order details: "+order);
        } catch (InvalidIdException e) {
            Log.info(e.getMessage());
        } catch (NoOrderFoundException e) {
            Log.info(e.getMessage());
        }
    }
}

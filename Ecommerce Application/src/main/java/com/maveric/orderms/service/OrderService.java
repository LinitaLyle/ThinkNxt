package com.maveric.orderms.service;

import com.maveric.customerms.domain.Customer;
import com.maveric.customerms.exception.InvalidIdException;
import com.maveric.customerms.exception.NoCustomerFoundException;
import com.maveric.customerms.service.CustomerService;
import com.maveric.orderms.dao.OrderDAO;
import com.maveric.orderms.domain.CreatedOrder;
import com.maveric.orderms.exceptions.InvalidPriceException;
import com.maveric.orderms.exceptions.InvalidProductNameException;
import com.maveric.orderms.exceptions.NoOrderFoundException;
import com.maveric.orderms.utilities.Utility;

import java.util.Optional;

public class OrderService implements IOrderService{

    private OrderDAO orderDAO = new OrderDAO();
    private CustomerService custSer;
    public OrderService(CustomerService custSer) {
        this.custSer=custSer;
    }

    @Override
    public CreatedOrder createOrder(long customerId, String productDesc, double productPrice) throws InvalidIdException, InvalidProductNameException, InvalidPriceException, NoCustomerFoundException {
        Utility.validateId(customerId, "Customer id cannot be <1");
        Utility.validateString(productDesc, 2,20, "Invalid product name");
        Utility.validatePrice(productPrice, "Product price must be > 0");
        Customer customer = custSer.findById(customerId);
        CreatedOrder order = new CreatedOrder(customer,productDesc,productPrice);
        CreatedOrder res = orderDAO.save(order);
        return res;
    }

    @Override
    public CreatedOrder findById(long orderId) throws InvalidIdException, NoOrderFoundException {
        Utility.validateId(orderId, "Invalid Order Id");
        Optional<CreatedOrder> order = orderDAO.findById(orderId);
        if (order.isPresent()) return order.get();
        throw new NoOrderFoundException("Order with id " + orderId + " not found");
    }
}

package com.maveric.orderms.service;

import com.maveric.customerms.exception.InvalidIdException;
import com.maveric.customerms.exception.NoCustomerFoundException;
import com.maveric.orderms.domain.CreatedOrder;
import com.maveric.orderms.exceptions.InvalidPriceException;
import com.maveric.orderms.exceptions.InvalidProductNameException;
import com.maveric.orderms.exceptions.NoOrderFoundException;

public interface IOrderService {
    CreatedOrder createOrder(long customerId, String productDesc, double productPrice) throws InvalidIdException, InvalidProductNameException, InvalidPriceException, NoCustomerFoundException;
    CreatedOrder findById(long orderId) throws InvalidIdException, NoOrderFoundException;
}

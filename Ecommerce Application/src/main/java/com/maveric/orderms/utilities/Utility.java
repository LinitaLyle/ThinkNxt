package com.maveric.orderms.utilities;

import com.maveric.customerms.exception.InsufficientBalanceException;
import com.maveric.customerms.exception.InvalidIdException;
import com.maveric.orderms.exceptions.InvalidPriceException;
import com.maveric.orderms.exceptions.InvalidProductNameException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.naming.InvalidNameException;

public class Utility {




    public static void validateString(String str, int minLen, int maxLen, String msg) throws InvalidProductNameException {
        if (str == null || str.isEmpty()) throw new InvalidProductNameException(msg);
        int strLen = str.length();
        if (strLen < minLen || strLen > maxLen) throw new InvalidProductNameException(msg);
    }

    public static void validatePrice(double price, String msg) throws InvalidPriceException {
        if (price<=0) throw new InvalidPriceException(msg);
    }

    public static void validateId(long id, String msg) throws InvalidIdException {
        if(id<1) throw new InvalidIdException(msg);
    }
}

package com.maveric.customerms.utilities;

import com.maveric.customerms.exception.InsufficientBalanceException;
import com.maveric.customerms.exception.InvalidIdException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.naming.InvalidNameException;

public class Utility {


    public static void validateString(String str, int minLen, int maxLen, String msg) throws InvalidNameException {
        if (str == null || str.isEmpty()) throw new InvalidNameException(msg);
        int strLen = str.length();
        if (strLen < minLen || strLen > maxLen) throw new InvalidNameException(msg);
        for (int i = 0; i < strLen; i++)
            if (!Character.isLetter(str.charAt(i)))
                throw new InvalidNameException(msg+" - name cannot contain special characters");
    }

    public static void validateBalance(double balance, String msg) throws InsufficientBalanceException {
        if (balance<5000) throw new InsufficientBalanceException(msg);
    }

    public static void validateId(long id, String msg) throws InvalidIdException {
        if(id<1) throw new InvalidIdException(msg);
    }
}

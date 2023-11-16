package com.maveric.projectms.utilities;

import com.maveric.projectms.exception.InvalidEmpNameException;
import com.maveric.projectms.exception.InvalidIdException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Utility {
    private static EntityManagerFactory emf;


    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf!=null){
            return emf;
        }
        emf = Persistence.createEntityManagerFactory("projectMS");
        return emf;
    }

    public void validateString(String str, int minLen, int maxLen, String msg) throws InvalidEmpNameException {
        if (str == null || str.isEmpty()) throw new InvalidEmpNameException(msg);
        int strLen = str.length();
        if (strLen < minLen || strLen > maxLen) throw new InvalidEmpNameException(msg);
        for (int i = 0; i < strLen; i++)
            if (!Character.isLetter(str.charAt(i)))
                throw new InvalidEmpNameException(msg+" name cannot contain special characters");
    }

    public void validateId(long id, String msg) throws InvalidIdException {
        if (id <= 0) throw new InvalidIdException(msg);
    }
}

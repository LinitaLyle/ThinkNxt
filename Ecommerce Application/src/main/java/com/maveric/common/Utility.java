package com.maveric.common;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Utility {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf!=null){
            return emf;
        }
        emf = Persistence.createEntityManagerFactory("ecommerceMS");
        return emf;
    }

    public static void closeEntityManagerFactory()
    {
        emf.close();
    }
}

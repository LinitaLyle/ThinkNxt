package com.maveric.projectms.utilities;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
    private static EntityManagerFactory emf;


    public static EntityManagerFactory getEntityManagerFactory() {
        if(emf!=null){
            return emf;
        }
        emf = Persistence.createEntityManagerFactory("projectMS");
        return emf;
    }
}

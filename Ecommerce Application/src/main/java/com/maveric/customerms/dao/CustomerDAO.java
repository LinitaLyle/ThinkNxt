package com.maveric.customerms.dao;

import com.maveric.customerms.domain.Customer;
import com.maveric.common.Utility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class CustomerDAO implements ICustomerDAO{

    private EntityManager entityManager;

    public CustomerDAO() {
        EntityManagerFactory emf = Utility.getEntityManagerFactory();
        this.entityManager = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Customer save(Customer customer) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        getEntityManager().persist(customer);
        transaction.commit();
        return customer;
    }

    @Override
    public Optional<Customer> findById(long id) {
        Optional<Customer> cust = Optional.ofNullable(getEntityManager().find(Customer.class, id));
        return cust;
    }

    
}

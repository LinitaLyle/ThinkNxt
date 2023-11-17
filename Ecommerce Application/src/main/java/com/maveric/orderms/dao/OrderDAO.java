package com.maveric.orderms.dao;

import com.maveric.common.Utility;
import com.maveric.orderms.domain.CreatedOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class OrderDAO implements IOrderDAO{
    private EntityManager entityManager;

    public OrderDAO() {
        EntityManagerFactory emf = Utility.getEntityManagerFactory();
        this.entityManager = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public CreatedOrder save(CreatedOrder createdOrder) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        //getEntityManager().persist(createdOrder);
        getEntityManager().merge(createdOrder);
        transaction.commit();
        return createdOrder;
    }

    @Override
    public Optional<CreatedOrder> findById(long id) {
        return Optional.ofNullable(getEntityManager().find(CreatedOrder.class, id));
    }

    public void close()
    {
        getEntityManager().close();
    }
}

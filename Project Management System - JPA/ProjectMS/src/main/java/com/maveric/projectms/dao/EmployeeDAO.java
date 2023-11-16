package com.maveric.projectms.dao;

import com.maveric.projectms.utilities.Utility;
import com.maveric.projectms.domain.Employee;
import jakarta.persistence.*;


import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements IEmployeeDAO {

    private EntityManager entityManager;

    public EmployeeDAO() {
        EntityManagerFactory emf = Utility.getEntityManagerFactory();
        entityManager = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Optional<Employee> findById(long id) {
        Optional<Employee> emp = Optional.ofNullable(getEntityManager().find(Employee.class, id));
        return emp;
    }

    @Override
    public Employee save(Employee employee) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        getEntityManager().persist(employee);
        transaction.commit();
        return employee;
    }


    public List<Employee> getAllEmployees() {
        String qText = "from Employee";
        TypedQuery<Employee> query = getEntityManager().createQuery(qText, Employee.class);
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }


}

package com.maveric.projectms.dao;

import com.maveric.projectms.domain.Employee;
import com.maveric.projectms.utilities.Utility;
import com.maveric.projectms.domain.Project;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDAO implements IProjectDAO {

    private EntityManager entityManager;
    EntityTransaction transaction;

    public ProjectDAO()
    {
       EntityManagerFactory emf = Utility.getEntityManagerFactory();
       this.entityManager = emf.createEntityManager();
       this.transaction = entityManager.getTransaction();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Optional<Project> findProjectById(long pId) {
        Optional<Project> resProj = Optional.ofNullable(getEntityManager().find(Project.class,pId));
        return resProj;
    }

    @Override
    public Project save(Project project) {
        EntityTransaction transaction = getEntityManager().getTransaction();
        transaction.begin();
        getEntityManager().persist(project);
        transaction.commit();
        return project;
    }

    public void merge(Project project, Employee emp)
    {
        transaction.begin();
        emp.setProjectWorkingOn(project);
        entityManager.merge(emp);
        transaction.commit();
    }


}

package com.maveric.projectms.dao;

import com.maveric.projectms.utilities.Util;
import com.maveric.projectms.domain.Project;
import jakarta.persistence.*;

import java.util.Optional;

public class ProjectDAO implements IProjectDAO {

    private EntityManager entityManager;

    public ProjectDAO()
    {
       EntityManagerFactory emf = Util.getEntityManagerFactory();
       this.entityManager = emf.createEntityManager();
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
}

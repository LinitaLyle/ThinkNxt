package com.maveric.projectms.dao;

import com.maveric.projectms.domain.Project;

import java.util.Optional;

public interface IProjectDAO {
    Optional<Project> findProjectById(long pId);
    Project save(Project project);
}

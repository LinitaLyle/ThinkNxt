package com.maveric.service;

import com.maveric.domain.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProjectDAO implements IProjectDAO {

    Map<Long, Project> projectStore = new HashMap<>();

    @Override
    public Optional<Project> findProjectById(long pId) {
        return Optional.ofNullable(projectStore.get(pId));
    }

    @Override
    public Project save(Project project) {
        projectStore.put(project.getId(), project);
        return project;
    }
}

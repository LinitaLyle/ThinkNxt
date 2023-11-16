package com.maveric.service;

import com.maveric.domain.Project;
import java.util.Optional;

public interface IProjectDAO {
    Optional<Project> findProjectById(long pId);
    Project save(Project project);
}

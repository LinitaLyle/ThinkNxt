package com.maveric.service;

import com.maveric.domain.Project;
import com.maveric.exception.InvalidEmpNameException;
import com.maveric.exception.InvalidIdException;
import com.maveric.exception.NoEmployeeFoundException;
import com.maveric.exception.ProjectNotFoundException;

import java.util.Collection;

public interface IProjectService {
    Project addProject(String projectName, Collection<String> technologies) throws InvalidEmpNameException;
    Project findProjectById(long pId) throws InvalidIdException, ProjectNotFoundException;
    void assignProject(long projectId, long employeeId) throws InvalidIdException, ProjectNotFoundException, NoEmployeeFoundException;
}

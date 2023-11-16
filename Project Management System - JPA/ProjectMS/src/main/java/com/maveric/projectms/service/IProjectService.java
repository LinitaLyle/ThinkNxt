package com.maveric.projectms.service;

import com.maveric.projectms.domain.Project;
import com.maveric.projectms.exception.InvalidEmpNameException;
import com.maveric.projectms.exception.InvalidIdException;
import com.maveric.projectms.exception.NoEmployeeFoundException;
import com.maveric.projectms.exception.ProjectNotFoundException;

import java.util.Collection;

public interface IProjectService {
    Project addProject(String projectName, Collection<String> technologies) throws InvalidEmpNameException;
    Project findProjectById(long pId) throws InvalidIdException, ProjectNotFoundException;
    void assignProject(long projectId, long employeeId) throws InvalidIdException, ProjectNotFoundException, NoEmployeeFoundException;
}

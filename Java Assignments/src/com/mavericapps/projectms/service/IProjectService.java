package com.mavericapps.projectms.service;

import com.mavericapps.customerms.exceptions.NoEmployeesRegisteredException;
import com.mavericapps.projectms.domain.Employee;
import com.mavericapps.projectms.domain.Project;
import com.mavericapps.projectms.exceptions.*;

import java.util.Collection;
import java.util.List;

public interface IProjectService {
    public Project addProject(String projectName, Collection<String> technologies) throws ProjectNameNullException;
    public Project findProjectById(int pid) throws InvalidProjectIdException, ProjectNotFoundException;
    public void assignProject(int projectId, int employeeId) throws InvalidEmployeeIdException, InvalidProjectIdException, EmployeeNotFoundException, ProjectNotFoundException;
    public List<Employee> findEmployeesWorkingOnProjectInAscFirstName(int projectId) throws InvalidProjectIdException, NoEmployeesRegisteredException, ProjectNotFoundException;

}

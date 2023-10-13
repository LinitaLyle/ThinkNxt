package com.mavericapps.projectms.service;

import com.mavericapps.customerms.exceptions.NoEmployeesRegisteredException;
import com.mavericapps.projectms.domain.Employee;
import com.mavericapps.projectms.domain.Project;
import com.mavericapps.projectms.exceptions.*;

import java.util.*;

public class ProjectService implements IProjectService {

    Map<Integer,Project> projectMap = new HashMap<>();
    int generatedProjectId;
    EmployeeService employeeService;

    public ProjectService(EmployeeService service)
    {
        this.generatedProjectId=1000;
        this.employeeService=service;
    }
    @Override
    public Project addProject(String projectName, Collection<String> technologies) throws projectNameNullException {
        Project resProject;
        if(projectName.isBlank())
            throw new projectNameNullException("EXCEPTION!!! Project name cannot be null!");
        int projId=getGeneratedProjectId();
        resProject = new Project(projId,projectName, (List<String>) technologies);
        projectMap.put(projId,resProject);
        return resProject;
    }
    @Override
    public Project findProjectById(int pid) throws InvalidProjectIdException {
        if(pid<=0)
            throw new InvalidProjectIdException("EXCEPTION!!! Invalid Project Id");
        for(Map.Entry<Integer, Project> entry:projectMap.entrySet())
        {
            int projectId = entry.getKey();
            if(projectId==pid)
                return entry.getValue();
        }
        return null;
    }
    @Override
    public void assignProject(int projectId, int employeeId) throws InvalidEmployeeIdException, InvalidProjectIdException, EmployeeNotFoundException, ProjectNotFoundException {

        Employee emp = employeeService.findEmployeeById(employeeId);
        if (emp==null)
            throw new EmployeeNotFoundException("Employee with id "+employeeId+" not found");
        Project project = findProjectById(projectId);
        if(project==null)
            throw new ProjectNotFoundException("Project with id "+projectId+" not found!!");
        List<Project> projectsWorkingOn = emp.getProjectsWorkingOn();
        projectsWorkingOn.add(project);
        emp.setProjectsWorkingOn(projectsWorkingOn);
    }
    @Override
    public List<Employee> findEmployeesWorkingOnProjectInAscFirstName(int projectId) throws InvalidProjectIdException, NoEmployeesRegisteredException {
        Project project = findProjectById(projectId);
        List<Employee> employeesInProject = new ArrayList<>();
        for(Employee emp:employeeService.getEmployees())
        {
            List<Project> projectsWorkingOn = emp.getProjectsWorkingOn();
            if(projectsWorkingOn.isEmpty())
                continue;
            if(projectsWorkingOn.contains(project))
                employeesInProject.add(emp);
        }
        return employeesInProject;
    }
    private int getGeneratedProjectId()
    {
        return ++generatedProjectId;
    }
}

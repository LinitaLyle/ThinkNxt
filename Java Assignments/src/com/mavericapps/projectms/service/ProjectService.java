package com.mavericapps.projectms.service;

import com.mavericapps.customerms.exceptions.InvalidCustomerId;
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
    public Project addProject(String projectName, Collection<String> technologies) throws ProjectNameNullException {
        validateString(projectName);
        Project resProject;
        int projId=getGeneratedProjectId();
        resProject = new Project(projId,projectName, (List<String>) technologies);
        projectMap.put(projId,resProject);
        return resProject;
    }
    @Override
    public Project findProjectById(int pid) throws InvalidProjectIdException, ProjectNotFoundException {
        validateProjectId(pid);
        Project proj = null;
        proj = projectMap.get(pid);
        if(proj == null)
            throw new ProjectNotFoundException("Project with id "+pid+ " not found" );
        return proj;
    }
    @Override
    public void assignProject(int projectId, int employeeId) throws InvalidEmployeeIdException, InvalidProjectIdException, EmployeeNotFoundException, ProjectNotFoundException {
        validateProjectId(projectId);
        validateEmployeeId(employeeId);
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
    public List<Employee> findEmployeesWorkingOnProjectInAscFirstName(int projectId) throws InvalidProjectIdException, NoEmployeesRegisteredException, ProjectNotFoundException {
        validateProjectId(projectId);
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

    private void validateString(String str) throws ProjectNameNullException {
        if(str==null) throw new ProjectNameNullException("Project Name cannot be null");
        if(str.isBlank()||str.isEmpty()) throw new ProjectNameNullException("Project name cannot be empty");
    }

    private void validateEmployeeId(int id) throws InvalidEmployeeIdException {
        if(id<1) throw new InvalidEmployeeIdException("Invalid employee id");
    }

    private void validateProjectId(int id) throws InvalidProjectIdException {
        if(id<1) throw new InvalidProjectIdException("Invalid project id");
    }
}

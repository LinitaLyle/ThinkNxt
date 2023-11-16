package com.maveric.projectms.service;

import com.maveric.projectms.dao.ProjectDAO;
import com.maveric.projectms.domain.Employee;
import com.maveric.projectms.domain.Project;
import com.maveric.projectms.exception.InvalidIdException;
import com.maveric.projectms.exception.InvalidEmpNameException;
import com.maveric.projectms.exception.NoEmployeeFoundException;
import com.maveric.projectms.exception.ProjectNotFoundException;
import com.maveric.projectms.utilities.Utility;
import jdk.jshell.execution.Util;

import java.util.*;

public class ProjectService implements IProjectService {

    private final ProjectDAO projDAO;
    private final EmployeeService empSer;

    private Utility util = new Utility();

    public ProjectService(EmployeeService empSer) {
        this.projDAO = new ProjectDAO();
        this.empSer = empSer;
    }

    @Override
    public Project addProject(String projectName, Collection<String> technologies) throws InvalidEmpNameException {
        util.validateString(projectName, 2, 10, "Invalid project name");
        Set<String> techStack = new HashSet<>(technologies);
        Project projObj = new Project(projectName, techStack);
        projDAO.save(projObj);
        return projObj;
    }

    @Override
    public Project findProjectById(long pId) throws InvalidIdException, ProjectNotFoundException {
        util.validateId(pId, "Invalid project id");
        Optional<Project> proj = projDAO.findProjectById(pId);
        if (proj.isPresent()) return proj.get();
        throw new ProjectNotFoundException("Project with id " + pId + " not found!!");
    }

    @Override
    public void assignProject(long projectId, long employeeId) throws InvalidIdException, ProjectNotFoundException, NoEmployeeFoundException {
        util.validateId(projectId, "Invalid project Id");
        util.validateId(employeeId, "Invalid employee Id");
        Project proj = findProjectById(projectId);
        Employee emp = empSer.findEmployeeById(employeeId);
        List<Employee> projEmployees = proj.getMembers();
        if(projEmployees==null)
            projEmployees = new ArrayList<>();
        else
            projEmployees.add(emp);
        proj.setMembers(projEmployees);
        projDAO.merge(proj, emp);
    }


}

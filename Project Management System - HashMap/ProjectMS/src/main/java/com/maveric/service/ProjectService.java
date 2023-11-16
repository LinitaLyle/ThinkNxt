package com.maveric.service;

import com.maveric.domain.Employee;
import com.maveric.domain.Project;
import com.maveric.exception.InvalidIdException;
import com.maveric.exception.InvalidEmpNameException;
import com.maveric.exception.NoEmployeeFoundException;
import com.maveric.exception.ProjectNotFoundException;

import java.util.*;

public class ProjectService implements IProjectService {

    private final ProjectDAO projDAO;
    private long generateProjId;
    private final EmployeeService empSer;

    public ProjectService(EmployeeService empSer) {
        this.projDAO = new ProjectDAO();
        this.generateProjId = 1000;
        this.empSer = empSer;
    }

    @Override
    public Project addProject(String projectName, Collection<String> technologies) throws InvalidEmpNameException {
        validateString(projectName, 2, 10, "Invalid project name");
        long projId = getGenerateProjId();
        Set<String> techStack = new HashSet<>(technologies);
        Project projObj = new Project(projId, projectName, techStack);
        projDAO.save(projObj);
        return projObj;
    }

    @Override
    public Project findProjectById(long pId) throws InvalidIdException, ProjectNotFoundException {
        validateId(pId, "Invalid project id");
        Optional<Project> proj = projDAO.findProjectById(pId);
        if (proj.isPresent()) return proj.get();
        throw new ProjectNotFoundException("Project with id " + pId + " not found!!");
    }

    @Override
    public void assignProject(long projectId, long employeeId) throws InvalidIdException, ProjectNotFoundException, NoEmployeeFoundException {
        validateId(projectId, "Invalid project Id");
        validateId(employeeId, "Invalid employee Id");
        Project proj = findProjectById(projectId);
        Employee emp = empSer.findEmployeeById(employeeId);
        List<Employee> projEmployees = proj.getMembers();
        projEmployees.add(emp);
        proj.setMembers(projEmployees);
        emp.setProjectWorkingOn(proj);
    }

    public void validateString(String str, int minLen, int maxLen, String msg) throws InvalidEmpNameException {
        if (str == null || str.isEmpty()) throw new InvalidEmpNameException(msg);
        int strLen = str.length();
        if (strLen < minLen || strLen > maxLen) throw new InvalidEmpNameException(msg);
        for (int i = 0; i < strLen; i++)
            if (!Character.isLetter(str.charAt(i))) throw new InvalidEmpNameException(msg);
    }

    public void validateId(long id, String msg) throws InvalidIdException {
        if (id <= 0) throw new InvalidIdException(msg);
    }

    public long getGenerateProjId() {
        return ++generateProjId;
    }


}

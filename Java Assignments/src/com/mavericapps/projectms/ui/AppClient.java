package com.mavericapps.projectms.ui;

import com.mavericapps.customerms.exceptions.NoEmployeesRegisteredException;
import com.mavericapps.projectms.domain.Employee;
import com.mavericapps.projectms.domain.Project;
import com.mavericapps.projectms.exceptions.*;
import com.mavericapps.projectms.service.EmployeeService;
import com.mavericapps.projectms.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

public class AppClient {

    public EmployeeService empService = new EmployeeService();
    public ProjectService projectService = new ProjectService(empService);

    public static void main(String[] args) {
        AppClient ac = new AppClient();
        ac.runApp();
    }

    public void runApp()
    {
        //Register an Employee
        registerEmployee("Sarah","Jones");
        registerEmployee("Adam","Brown");
        registerEmployee("Linita","Lyle");
        registerEmployee("Daniel","Robert");
        registerEmployee("Bhavitha","Reddy");
        displayAllEmployees();


        System.out.println("\n**********Find Employee by id**********");
        int empId=1;
        findEmployeebyId(empId);
        findEmployeebyId(-1); //throws exception

        System.out.println("\n**********Sort employees on first name**********");
        findEmployeesAscByFirstName();

        System.out.println("\n**********Add Projects**********");
        String project1Name = "ThinkNxt";
        List<String> technologies1 = new ArrayList<>();
        technologies1.add("SQL");
        technologies1.add("Git");
        technologies1.add("Java");
        addProject(project1Name,technologies1);

        String project2Name = "FrescoPlay";
        List<String> technologies2 = new ArrayList<>();
        technologies2.add("SQL");
        technologies2.add("Git");
        technologies2.add("Dot Net");
        addProject(project2Name,technologies2);

        String project3Name = "   ";
        addProject(project3Name,technologies1);//throws exception

        System.out.println("\n**********Find project by Id**********");
        findProject(1000);//negative case
        findProject(1001);
        findProject(0); // throws exception

        System.out.println("\n**********Assign Project to Employees**********");
        assignProject(1001,1);
        assignProject(1002,2);
        assignProject(1002,3);
        assignProject(1001,4);
        assignProject(1001,5);
        assignProject(1002,1);
        assignProject(1005,1);//throws project does not exist
        assignProject(1001,8);//throws employee does not exist exception
        displayAllEmployees();

        System.out.println("\n**********Find Employees in project**********");
        int projectId=1001;
        findEmployeesWorkingOnProjectInAscFirstName(projectId);
  }

    private void findEmployeesAscByFirstName() {

        try {
            List<Employee> sortedEmployees = empService.findEmployeesAscByFirstName();
            for(Employee emp: sortedEmployees)
                System.out.println(emp);
        } catch (NoEmployeesRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void registerEmployee(String firstName, String lastName)
    {
        empService.registerEmployee(firstName,lastName);
    }
    public void findEmployeebyId(int empId)
    {
        try {
            Employee resEmp = empService.findEmployeeById(empId);
            if(resEmp==null)
                System.out.println("Employee with id "+empId+" not found!");
            else
                System.out.println("Employee details: "+resEmp);
        } catch (InvalidEmployeeIdException | EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addProject(String projectName, List<String> technologies)
    {
        try {
            Project project = projectService.addProject(projectName,technologies);
            if(project==null)
                System.out.println("Project "+projectName+" not found!");
            else
                System.out.println("Project added: "+project);
        } catch (projectNameNullException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findProject(int projId)
    {
        try {
            Project resObj =projectService.findProjectById(projId);
            if(resObj==null)
                System.out.println("Project with id "+projId+" not found!!");
            else
                System.out.println("Project details: "+resObj);
        } catch (InvalidProjectIdException e) {
            System.out.println(e.getMessage());
        }
    }

    public void assignProject(int projId, int empId) {

        try {
            projectService.assignProject(projId, empId);
        } catch (InvalidEmployeeIdException e) {
            System.out.println(e.getMessage());
        } catch (InvalidProjectIdException e) {
            System.out.println(e.getMessage());
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ProjectNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findEmployeesWorkingOnProjectInAscFirstName(int projId) {
        try {
            List<Employee> projectEmployees = projectService.findEmployeesWorkingOnProjectInAscFirstName(projId);
            if(projectEmployees!=null)
            {
                System.out.println("The employees working on project "+projId+" are: ");
                for(Employee empObj:projectEmployees)
                    System.out.println(empObj.getFirstName()+" "+empObj.getLastName());
            }
        } catch (InvalidProjectIdException e) {
            System.out.println(e.getMessage());
        } catch (NoEmployeesRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayAllEmployees(){
        try {
            List<Employee> allEmployees = empService.getEmployees();
            System.out.println(allEmployees);
        } catch (NoEmployeesRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }
}


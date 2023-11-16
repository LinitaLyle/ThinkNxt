package com.maveric.projectms.ui;

import com.maveric.projectms.domain.Employee;
import com.maveric.projectms.domain.Project;
import com.maveric.projectms.exception.InvalidIdException;
import com.maveric.projectms.exception.InvalidEmpNameException;
import com.maveric.projectms.exception.NoEmployeeFoundException;
import com.maveric.projectms.exception.ProjectNotFoundException;
import com.maveric.projectms.service.EmployeeService;
import com.maveric.projectms.service.ProjectService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class AppClient {

    private EmployeeService empServ = new EmployeeService();
    private ProjectService projSer = new ProjectService(empServ);

    public static void main(String[] args) {
        AppClient appClient = new AppClient();
        appClient.runApp();

    }

    public void runApp()
    {
        System.out.println("************EMPLOYEE SERVICES***************");

        findEmployeeById(1); //throws no employees found exception
        findEmployeesAscByFirstName(); // throws no employees found exception

        System.out.println("\n--------Register Employee----------");
        registerEmployee("Linita","Lyle");
        registerEmployee("Bhavitha","Reddy");
        registerEmployee("Sushma","Nayak");
        registerEmployee("Reshma","MD");//throws exception
        registerEmployee("J","Dones"); //throws exception
        registerEmployee("Monisha","Elizabeth");
        registerEmployee("Sneha","Murugan");

        System.out.println("\n--------Find Employee by Id----------");
        findEmployeeById(0); //Invalid emp id - throws exception
        findEmployeeById(2);
        findEmployeeById(5);
        findEmployeeById(70); // Employee not found exception

        System.out.println("\n--------Sort Employees by First Name----------");
        findEmployeesAscByFirstName();

        System.out.println("************PROJECT SERVICES***************");
        findProjectById(10000); // throw exception

        System.out.println("\n--------Add Project----------");
        String projectName1 = "Citi";
        Collection<String> techStack1 = new HashSet<>();
        techStack1.add("MySQL");
        techStack1.add("PHP");
        addProject(projectName1,techStack1);

        String projectName2 = "Metro";
        Collection<String> techStack2 = new HashSet<>();
        techStack2.add("MongoDB");
        techStack2.add("Express.js");
        techStack2.add("Angular");
        addProject(projectName2,techStack2);

        String projectName3 = "JPMorgan";
        Collection<String> techStack3 = new HashSet<>();
        techStack3.add("Python");
        techStack3.add("Django");
        techStack3.add("HTML/CSS");
        addProject(projectName3,techStack3);

        String projectName4 = "";
        addProject(projectName4, techStack1); // throws exception

        System.out.println("\n--------Find Project by Id----------");
        findProjectById(1);
        findProjectById(-1); //throws invalid proj id exception
        findProjectById(21); //throws project not found exception

        System.out.println("\n--------Assign Project to Employee----------");
        assignProject(3,1);
       assignProject(1,2);
      assignProject(2,3);
       assignProject(1,4);
      assignProject(1,5);
   }

    private void assignProject(long projId, long empId) {
        try {
            projSer.assignProject(projId,empId);
            System.out.println("Project Assigned!");
            findProjectById(projId);
            findEmployeeById(empId);
        } catch (InvalidIdException e) {
            System.out.println(e.getMessage());
        } catch (ProjectNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NoEmployeeFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProject(String projectName1, Collection<String> techStack1) {
        try {
            Project proj = projSer.addProject(projectName1,techStack1);
            System.out.println("Project added - "+proj);
        } catch (InvalidEmpNameException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findProjectById(long projId) {
        Project proj = null;
        try {
            proj = projSer.findProjectById(projId);
            System.out.println("Project details : "+proj);
        } catch (InvalidIdException e) {
            System.out.println(e.getMessage());
        } catch (ProjectNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void registerEmployee(String firstName, String lastName)
    {
        try {
            empServ.registerEmployee(firstName, lastName);
            System.out.println(firstName+" "+lastName+" - Employee registered..");
        } catch (InvalidEmpNameException e) {
            System.out.println(e.getMessage());
        }

    }

    public void findEmployeeById(long id)
    {
        Employee emp = null;
        try {
            emp = empServ.findEmployeeById(id);
            System.out.println("Employee found! - "+emp);
        } catch (InvalidIdException e) {
            System.out.println(e.getMessage());
        } catch (NoEmployeeFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void findEmployeesAscByFirstName()
    {
        List<Employee> resEmp = null;
        try {
            resEmp = empServ.findEmployeesAscByFirstName();
            System.out.println("Employees sorted by first name: ");
            for(Employee emp: resEmp)
                System.out.println(emp);
        } catch (NoEmployeeFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}

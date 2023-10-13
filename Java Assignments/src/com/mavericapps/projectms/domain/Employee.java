package com.mavericapps.projectms.domain;

import java.util.ArrayList;
import java.util.List;

public class Employee implements Comparable<Employee> {
    private int id;
    private String firstName;
    private String lastName;
    private List<Project> projectsWorkingOn;

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.projectsWorkingOn = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Project> getProjectsWorkingOn() {
        return projectsWorkingOn;
    }

    public void setProjectsWorkingOn(List<Project> projectsWorkingOn) {
        this.projectsWorkingOn = projectsWorkingOn;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", projectsWorkingOn=" + projectsWorkingOn +
                '}'+'\n';
    }

    @Override
    public int compareTo(Employee emp) {
        return this.getFirstName().compareTo(emp.getFirstName());
    }
}

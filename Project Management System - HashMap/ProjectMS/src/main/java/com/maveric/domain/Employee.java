package com.maveric.domain;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private long id;
    private String firstName;
    private String lastName;
    private Project projectWorkingOn;

    public Employee(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setProjectWorkingOn(Project projectWorkingOn) {
        this.projectWorkingOn = projectWorkingOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(projectWorkingOn, employee.projectWorkingOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", projectWorkingOn=" + projectWorkingOn +
                '}';
    }

    @Override
    public int compareTo(Employee emp) {
        return this.firstName.compareTo(emp.firstName);
    }
}

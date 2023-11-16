package com.maveric.projectms.domain;

import jakarta.persistence.*;

import java.util.Objects;
@Table(name="JPA_Employee")
@Entity
public class Employee implements Comparable<Employee> {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="employee_id")
    private long id;
    @Column(name="first_name",nullable = false)
    private String firstName;
    @Column(name="last_name",nullable = false)
    private String lastName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_working_on")
    private Project projectWorkingOn;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Employee(){}
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

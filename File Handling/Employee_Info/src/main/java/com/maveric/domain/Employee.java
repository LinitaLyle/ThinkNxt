package com.maveric.domain;

import java.util.Objects;

public class Employee {
    private final int id;
    private final String name;
    private double salary;
    private String dept;
    private String address;

    public Employee(int id, String name, double salary, String dept, String address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dept = dept;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name) && Objects.equals(dept, employee.dept) && Objects.equals(address, employee.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", dept='" + dept + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

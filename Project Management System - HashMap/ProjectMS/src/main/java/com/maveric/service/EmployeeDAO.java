package com.maveric.service;

import com.maveric.domain.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmployeeDAO implements IEmployeeDAO {

    private Map<Long, Employee> employeeMap = new HashMap<>();

    @Override
    public Optional<Employee> findById(long id) {
        Optional<Employee> emp = Optional.ofNullable(employeeMap.get(id));
        return emp;
    }

    @Override
    public Employee save(Employee employee) {
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    public Map<Long, Employee> getEmployeeMap() {
        return employeeMap;
    }
}

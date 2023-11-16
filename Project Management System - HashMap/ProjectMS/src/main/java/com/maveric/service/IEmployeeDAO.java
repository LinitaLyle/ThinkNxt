package com.maveric.service;

import com.maveric.domain.Employee;

import java.util.Optional;

public interface IEmployeeDAO {
    Optional<Employee> findById(long id);
    Employee save(Employee employee);
}

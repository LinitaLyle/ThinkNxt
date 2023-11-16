package com.maveric.projectms.dao;

import com.maveric.projectms.domain.Employee;

import java.util.Optional;

public interface IEmployeeDAO {
    Optional<Employee> findById(long id);
    Employee save(Employee employee);
}

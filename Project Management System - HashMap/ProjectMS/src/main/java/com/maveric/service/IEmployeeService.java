package com.maveric.service;

import com.maveric.domain.Employee;
import com.maveric.exception.InvalidIdException;
import com.maveric.exception.InvalidEmpNameException;
import com.maveric.exception.NoEmployeeFoundException;

import java.util.List;

public interface IEmployeeService {
    void registerEmployee(String firstName, String lastName) throws InvalidEmpNameException;
    Employee findEmployeeById(long eId) throws InvalidIdException, NoEmployeeFoundException;
    List<Employee> findEmployeesAscByFirstName() throws NoEmployeeFoundException;
}

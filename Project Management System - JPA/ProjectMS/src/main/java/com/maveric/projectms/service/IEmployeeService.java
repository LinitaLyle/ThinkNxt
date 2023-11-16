package com.maveric.projectms.service;

import com.maveric.projectms.domain.Employee;
import com.maveric.projectms.exception.InvalidIdException;
import com.maveric.projectms.exception.InvalidEmpNameException;
import com.maveric.projectms.exception.NoEmployeeFoundException;

import java.util.List;

public interface IEmployeeService {
    void registerEmployee(String firstName, String lastName) throws InvalidEmpNameException;
    Employee findEmployeeById(long eId) throws InvalidIdException, NoEmployeeFoundException;
    List<Employee> findEmployeesAscByFirstName() throws NoEmployeeFoundException;
}

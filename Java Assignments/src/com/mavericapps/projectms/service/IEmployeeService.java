package com.mavericapps.projectms.service;

import com.mavericapps.customerms.exceptions.InvalidNameException;
import com.mavericapps.customerms.exceptions.NoEmployeesRegisteredException;
import com.mavericapps.projectms.domain.Employee;
import com.mavericapps.projectms.exceptions.EmployeeNotFoundException;
import com.mavericapps.projectms.exceptions.InvalidEmployeeIdException;

import java.util.List;

public interface IEmployeeService {
    public void registerEmployee(String firstName, String lastName) throws InvalidNameException;
    public Employee findEmployeeById(int eid) throws InvalidEmployeeIdException, EmployeeNotFoundException;
    public List<Employee> findEmployeesAscByFirstName() throws NoEmployeesRegisteredException;

}

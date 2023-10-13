package com.mavericapps.projectms.service;

import com.mavericapps.customerms.exceptions.NoEmployeesRegisteredException;
import com.mavericapps.projectms.domain.Employee;
import com.mavericapps.projectms.exceptions.EmployeeNotFoundException;
import com.mavericapps.projectms.exceptions.InvalidEmployeeIdException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    private static int generatedEmployeeId;
    private List<Employee> employees = new ArrayList<>();
    @Override
    public void registerEmployee(String firstName, String lastName)
    {
        int employeeId = getGeneratedEmployeeId();
        Employee emp = new Employee(employeeId,firstName,lastName);
        employees.add(emp);
    }
    @Override
    public Employee findEmployeeById(int eid) throws InvalidEmployeeIdException, EmployeeNotFoundException {
        validateId(eid);
        Employee resEmp = null;
        for(Employee emp:employees)
            if(emp.getId()==eid)
                return emp;

        throw new EmployeeNotFoundException("Employee with id "+eid+" not found!");
    }
    @Override
    public List<Employee> findEmployeesAscByFirstName() throws NoEmployeesRegisteredException {
        if(employees.isEmpty())
            throw new NoEmployeesRegisteredException("No employee has been registered yet!");
        Collections.sort(employees);
        return employees;
    }

    private int getGeneratedEmployeeId()
    {
        return ++generatedEmployeeId;
    }

    public List<Employee> getEmployees() throws NoEmployeesRegisteredException {
        if (employees.isEmpty())
            throw new NoEmployeesRegisteredException("No employee has been registered yet!");
        return employees;
    }

    public void validateId(int id) throws InvalidEmployeeIdException {
        if(id<=0)
            throw new InvalidEmployeeIdException("EXCEPTION!!! Invalid employee id");

    }
}

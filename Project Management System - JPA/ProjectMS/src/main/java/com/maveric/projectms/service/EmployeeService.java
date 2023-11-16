package com.maveric.projectms.service;

import com.maveric.projectms.dao.EmployeeDAO;
import com.maveric.projectms.domain.Employee;
import com.maveric.projectms.exception.InvalidIdException;
import com.maveric.projectms.exception.InvalidEmpNameException;
import com.maveric.projectms.exception.NoEmployeeFoundException;
import com.maveric.projectms.utilities.Utility;

import java.util.*;

public class EmployeeService implements IEmployeeService {

    private EmployeeDAO empDAO = new EmployeeDAO();
    private Utility util = new Utility();

    @Override
    public void registerEmployee(String firstName, String lastName) throws InvalidEmpNameException {
        util.validateString(firstName, 3, 10, "Invalid first name");
        util.validateString(lastName, 3, 10, "Invalid last name");
        Employee emp = new Employee(firstName, lastName);
        empDAO.save(emp);
    }

    @Override
    public Employee findEmployeeById(long eId) throws InvalidIdException, NoEmployeeFoundException {
        util.validateId(eId, "Invalid employee Id");
        Optional<Employee> emp = empDAO.findById(eId);
        if (emp.isPresent()) return emp.get();
        throw new NoEmployeeFoundException("Employee with id " + eId + " not found");
    }

    @Override
    public List<Employee> findEmployeesAscByFirstName() throws NoEmployeeFoundException {
        List<Employee> employees = empDAO.getAllEmployees();
        if (employees.isEmpty()) throw new NoEmployeeFoundException("No employee found");
        Collections.sort(employees);
        return employees;
    }




}

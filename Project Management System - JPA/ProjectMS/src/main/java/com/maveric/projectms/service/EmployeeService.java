package com.maveric.projectms.service;

import com.maveric.projectms.dao.EmployeeDAO;
import com.maveric.projectms.domain.Employee;
import com.maveric.projectms.exception.InvalidIdException;
import com.maveric.projectms.exception.InvalidEmpNameException;
import com.maveric.projectms.exception.NoEmployeeFoundException;

import java.util.*;

public class EmployeeService implements IEmployeeService {

    private EmployeeDAO empDAO = new EmployeeDAO();

    @Override
    public void registerEmployee(String firstName, String lastName) throws InvalidEmpNameException {
        validateString(firstName, 3, 10, "Invalid first name");
        validateString(lastName, 3, 10, "Invalid last name");
        Employee emp = new Employee(firstName, lastName);
        empDAO.save(emp);
    }

    @Override
    public Employee findEmployeeById(long eId) throws InvalidIdException, NoEmployeeFoundException {
        validateId(eId);
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


    private void validateString(String str, int minLen, int maxLen, String msg) throws InvalidEmpNameException {
        if (str == null || str.isEmpty()) throw new InvalidEmpNameException(msg);
        int strLen = str.length();
        if (strLen < minLen || strLen > maxLen) throw new InvalidEmpNameException(msg);
        for (int i = 0; i < strLen; i++)
            if (!Character.isLetter(str.charAt(i)))
                throw new InvalidEmpNameException(msg+" name cannot contain special characters");
    }

    private void validateId(long id) throws InvalidIdException {
        if (id <= 0) throw new InvalidIdException("Invalid id");
    }

}

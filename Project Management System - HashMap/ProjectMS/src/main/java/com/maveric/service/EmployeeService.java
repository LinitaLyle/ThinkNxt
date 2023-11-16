package com.maveric.service;

import com.maveric.domain.Employee;
import com.maveric.exception.InvalidIdException;
import com.maveric.exception.InvalidEmpNameException;
import com.maveric.exception.NoEmployeeFoundException;

import java.util.*;

public class EmployeeService implements IEmployeeService {

    private EmployeeDAO empDAO = new EmployeeDAO();
    private long generatedEmpId;

    @Override
    public void registerEmployee(String firstName, String lastName) throws InvalidEmpNameException {
        validateString(firstName, 3, 10, "Invalid first name");
        validateString(lastName, 3, 10, "Invalid last name");
        long empId = getGeneratedEmpId();
        Employee emp = new Employee(empId, firstName, lastName);
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
        Collection<Employee> employees = empDAO.getEmployeeMap().values();
        if (employees.isEmpty()) throw new NoEmployeeFoundException("No employee in store");
        List<Employee> resEmp = new ArrayList<>(employees);
        Collections.sort(resEmp);
        return resEmp;
    }

    private long getGeneratedEmpId() {
        return ++generatedEmpId;
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

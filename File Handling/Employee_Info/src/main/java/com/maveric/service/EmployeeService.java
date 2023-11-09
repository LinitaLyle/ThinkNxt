package com.maveric.service;

import com.maveric.domain.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {

    List<Employee> employeeList = new ArrayList<>();

    public void readFile(final String fileName) {

        File inputFile = new File(fileName);
        try (
                Reader reader = new FileReader(inputFile);
                BufferedReader br = new BufferedReader(reader);
        ) {

            String empLine;
            while ((empLine = br.readLine()) != null) {
                String[] empDetails = empLine.split(",");
                int empId = Integer.parseInt(empDetails[0]);
                String empName = empDetails[1];
                double empSal = Double.parseDouble(empDetails[2]);
                String empDep = empDetails[3];
                StringBuilder empAdd = new StringBuilder();
                int addLen = empDetails.length;
                for (int i = 4; i < addLen; i++)
                    empAdd.append(empDetails[i]).append(",");
                String empAddress = empAdd.toString().replaceAll(",$", "");

                Employee emp = new Employee(empId, empName, empSal, empDep, empAddress);
                employeeList.add(emp);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void writeFile(final String fileName)
    {


    }

}


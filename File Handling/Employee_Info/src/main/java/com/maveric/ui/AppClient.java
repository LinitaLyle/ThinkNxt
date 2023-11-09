package com.maveric.ui;

import com.maveric.service.EmployeeService;

public class AppClient {

    public final EmployeeService empSer = new EmployeeService();

    public static void main(String[] args) {

        AppClient appClient = new AppClient();
        appClient.runApp();

    }

    public void runApp() {
        String fileName = "C:\\Users\\linital\\Desktop\\Fresco\\ThinkNxt - Training\\ThinkNxt Repository\\Java Task 8\\Employee_Info\\src\\main\\resources\\Employee Details.txt";
        empSer.readFile(fileName);
    }
}

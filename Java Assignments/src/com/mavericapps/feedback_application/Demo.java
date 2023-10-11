package com.mavericapps.feedback_application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Demo {
    ArrayList<Feedback> feedbacks = new ArrayList();
    ArrayList<Customer> customers = new ArrayList();

    public Demo() {
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Customer c1 = new Customer(1001, "Albert Jones");
        Customer c2 = new Customer(1002, "Christine Jerry");
        Customer c3 = new Customer(1003, "Daniel Weston");
        Customer c4 = new Customer(1004, "Fedrick Brown");
        Customer c5 = new Customer(1005, "Georgie");
        demo.addCustomers(c1);
        demo.addCustomers(c2);
        demo.addCustomers(c3);
        demo.addCustomers(c4);
        demo.addCustomers(c5);
        System.out.println("\nThe customers are: ");
        demo.printCustomers();
        Feedback f1 = new Feedback(1, "Great Interface", c1);
        Feedback f2 = new Feedback(1, "Beautiful site!", c2);
        Feedback f3 = new Feedback(1, "Improve customer service", c3);
        Feedback f4 = new Feedback(1, "Need more products", c4);
        Feedback f5 = new Feedback(1, "Site too slow", c1);
        Feedback f6 = new Feedback(1, "Good services", c3);
        Feedback f7 = new Feedback(1, "Irrelevant products", c4);
        Feedback f8 = new Feedback(1, "Easy navigation", c1);
        Feedback f9 = new Feedback(1, "Satisfied", c5);
        Feedback f10 = new Feedback(1, "Could be better", c5);
        demo.addFeedback(f1);
        demo.addFeedback(f2);
        demo.addFeedback(f3);
        demo.addFeedback(f4);
        demo.addFeedback(f5);
        demo.addFeedback(f6);
        demo.addFeedback(f7);
        demo.addFeedback(f8);
        demo.addFeedback(f9);
        demo.addFeedback(f10);
        System.out.println("\nThe feedbacks received: ");
        demo.printFeedbacks();
        System.out.println("\nFetch feedbacks by customer id: ");
        Scanner sc = new Scanner(System.in);
        int cid = sc.nextInt();
        ArrayList<Feedback> fdList = demo.fetchFeedbacks(cid);
        Iterator var20 = fdList.iterator();

        while(var20.hasNext()) {
            Feedback fd = (Feedback)var20.next();
            System.out.println(fd);
        }

    }

    public void addFeedback(Feedback fb) {
        this.feedbacks.add(fb);
    }

    public void addCustomers(Customer cust) {
        this.customers.add(cust);
    }

    public void printCustomers() {
        Iterator var1 = this.customers.iterator();

        while(var1.hasNext()) {
            Customer cust = (Customer)var1.next();
            System.out.println(cust);
        }

    }

    public void printFeedbacks() {
        Iterator var1 = this.feedbacks.iterator();

        while(var1.hasNext()) {
            Feedback f = (Feedback)var1.next();
            System.out.println(f);
        }

    }

    public ArrayList<Feedback> fetchFeedbacks(int custId) {
        ArrayList<Feedback> feedbackRes = new ArrayList();
        Iterator var3 = this.feedbacks.iterator();

        while(var3.hasNext()) {
            Feedback fd = (Feedback)var3.next();
            if (fd.getCustomer().getCustId() == custId) {
                feedbackRes.add(fd);
            }
        }

        return feedbackRes;
    }
}

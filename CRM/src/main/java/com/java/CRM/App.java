package com.java.CRM;
import jakarta.persistence.*;


import java.util.*;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crmPU");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        CustomerService customerService = new CustomerService(em);
        LeadService leadService = new LeadService(em);
        ProductService productService = new ProductService(em);
        OrderService orderService = new OrderService(em);
        TicketService ticketService = new TicketService(em);
        ReportService reportService = new ReportService(em);

        while (true) {

            System.out.println("1.Register Customer");
            System.out.println("2.Add Address");
            System.out.println("3.Create Lead");
            System.out.println("4.Assign Lead");
            System.out.println("5.Convert Lead");
            System.out.println("6.Add Product");
            System.out.println("7.Place Order");
            System.out.println("8.Raise Ticket");
            System.out.println("9.Employee Performance");
            System.out.println("10.Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    customerService.registerCustomer(name, email, phone);
                    break;

                case 6:
                    sc.nextLine();
                    System.out.print("Product Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    productService.addProduct(pname, price);
                    break;

                case 10:
                    em.close();
                    emf.close();
                    System.exit(0);
            }
        }
    }
}
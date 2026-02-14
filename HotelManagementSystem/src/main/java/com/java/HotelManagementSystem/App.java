package com.java.HotelManagementSystem;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;

public class App {

    static SessionFactory sf;

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Booking.class);
        sf = cfg.buildSessionFactory();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("1.Add");
            System.out.println("2.View");
            System.out.println("3.Update");
            System.out.println("4.Delete");
            System.out.println("5.Exit");

            int ch = sc.nextInt();

            if (ch == 1) {
                addBooking();
            }
            else if (ch == 2) {
                viewBooking();
            }
            else if (ch == 3) {
                updateBooking();
            }
            else if (ch == 4) {
                deleteBooking();
            }
            else if (ch == 5) {
                sf.close();
                break;
            }
            else {
                System.out.println("Wrong Choice");
            }
        }
    }

    public static void addBooking() {

        Scanner sc = new Scanner(System.in);

        sc.nextLine();
        System.out.println("Name:");
        String name = sc.nextLine();

        System.out.println("Room:");
        String room = sc.nextLine();

        System.out.println("CheckIn (YYYY-MM-DD):");
        String in = sc.nextLine();

        System.out.println("CheckOut (YYYY-MM-DD):");
        String out = sc.nextLine();

        Booking b = new Booking(name, room, in, out);

        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        s.save(b);

        t.commit();
        s.close();

        System.out.println("Added");
    }

    public static void viewBooking() {

        Session s = sf.openSession();

        List<Booking> list = s.createQuery("from Booking", Booking.class).list();

        for (Booking b : list) {
        	 System.out.println("ID: " + b.getBookingId());
             System.out.println("Customer: " + b.getCustomerName());
             System.out.println("Room Type: " + b.getRoomType());
             System.out.println("CheckIn: " + b.getCheckInDate());
             System.out.println("CheckOut: " + b.getCheckOutDate());
             System.out.println("Total: " + b.getTotalAmount());
        }

        s.close();
    }

    public static void updateBooking() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Id:");
        int id = sc.nextInt();
        sc.nextLine();

        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        Booking b = s.get(Booking.class, id);

        if (b != null) {

            System.out.println("New Room:");
            String room = sc.nextLine();

            b.setRoomType(room);

            s.update(b);

            System.out.println("Updated");

        } else {
            System.out.println("Not Found");
        }

        t.commit();
        s.close();
    }

    public static void deleteBooking() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Id:");
        int id = sc.nextInt();

        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        Booking b = s.get(Booking.class, id);

        if (b != null) {
            s.delete(b);
            System.out.println("Deleted");
        } else {
            System.out.println("Not Found");
        }

        t.commit();
        s.close();
    }
}

package com.java.CRM;
import jakarta.persistence.*;

public class TicketService {

    private EntityManager em;

    public TicketService(EntityManager em) {
        this.em = em;
    }

    public void raiseTicket(Long orderId, String issueDescription) {

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Order order = em.find(Order.class, orderId);

            SupportTicket ticket = new SupportTicket();
            ticket.setOrder(order);

            em.persist(ticket);
            tx.commit();

            System.out.println("Ticket Raised!");
        } catch (Exception e) {
            tx.rollback();
        }
    }
}
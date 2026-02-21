package com.java.CRM;
import jakarta.persistence.*;

public class LeadService {

    private EntityManager em;

    public LeadService(EntityManager em) {
        this.em = em;
    }

    public void createLead(String name, String source, String contactInfo) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Lead lead = new Lead();
            lead.setName(name);
            lead.setSource(source);
            lead.setContactInfo(contactInfo);
            em.persist(lead);
            tx.commit();
            System.out.println("Lead Created!");
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void assignLeadToEmployee(Long leadId, Long employeeId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Lead lead = em.find(Lead.class, leadId);
            SalesEmployee emp = em.find(SalesEmployee.class, employeeId);
            lead.setEmployee(emp);
            tx.commit();
            System.out.println("Lead Assigned!");
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void convertLeadToCustomer(Long leadId) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Lead lead = em.find(Lead.class, leadId);
            Customer customer = new Customer();
            customer.setName(lead.getName());
            customer.setEmail(lead.getContactInfo());
            em.persist(customer);
            em.remove(lead);
            tx.commit();
            System.out.println("Lead Converted!");
        } catch (Exception e) {
            tx.rollback();
        }
    }
}
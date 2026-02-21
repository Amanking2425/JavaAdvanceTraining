package com.java.CRM;

import jakarta.persistence.*;

public class ProductService {

    private EntityManager em;

    public ProductService(EntityManager em) {
        this.em = em;
    }

    public void addProduct(String name, double price) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Product p = new Product();
            p.setProductName(name);
            p.setPrice(price);
            em.persist(p);
            tx.commit();
            System.out.println("Product Added!");
        } catch (Exception e) {
            tx.rollback();
        }
    }
}
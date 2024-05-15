package com.seleniumexpress.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Unit test for simple App.
 */


public class anu {
    public static void main (String[] args){
        save_my_first();
    }
    public static void save_my_first() {
        student student = new student(1,"Aron",18,"IMT");
        Configuration con = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = con.buildSessionFactory();

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
            System.out.println("Wowww");
        }
    }
}
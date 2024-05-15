package com.seleniumexpress.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Unit test for simple App.
 */


public class anu {
    public static void main (String[] args){
        saveStudentToDatabase(1, "Aryo", 19, "IMT");
        updateStudentInDatabase(2, "Aaaryo", 20, "IMT");
        selectStudentFromDatabase(1);
        deleteStudentFromDatabase(1);
    }

    public static void saveStudentToDatabase(int id, String name, int age, String major) {
        Student newStudent = new Student(id, name, age, major);
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(newStudent);
            session.getTransaction().commit();
            System.out.println("Student saved successfully!");
        } finally {
            sessionFactory.close();
        }
    }

    public static void updateStudentInDatabase(int id, String name, int age, String major) {
        Student updatedStudent = new Student(id, name, age, major);
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.refresh(updatedStudent);
            session.getTransaction().commit();
            System.out.println("Student updated successfully!");
        } finally {
            sessionFactory.close();
        }
    }

    public static void selectStudentFromDatabase(int id) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student selectedStudent = session.get(Student.class, id);
            session.getTransaction().commit();
            if (selectedStudent != null) {
                System.out.println("Student selected: " + selectedStudent.getName());
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } finally {
            sessionFactory.close();
        }
    }

    public static void deleteStudentFromDatabase(int id) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Student studentToDelete = session.get(Student.class, id);
            if (studentToDelete != null) {
                session.remove(studentToDelete);
                session.getTransaction().commit();
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } finally {
            sessionFactory.close();
        }
    }
}
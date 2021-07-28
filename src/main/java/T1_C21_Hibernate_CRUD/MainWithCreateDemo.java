/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1_C21_Hibernate_CRUD;

import T1_C20_Hibernate_Config_With_Annotations_Entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class MainWithCreateDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg_C21.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // use session to save object
            System.out.println("Creating new student object ...");
            Student temStudent = new Student("Paul", "Johnson", "pj@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student ...");
            session.save(temStudent);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception e) {

        } finally {
            factory.close();
        }

    }

}

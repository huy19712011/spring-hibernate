/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1_C21_Hibernate_CRUD;

import T1_C20_Hibernate_Config_With_Annotations_Entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class MainWithReadDemo {

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
            Student temStudent = new Student("Lionel", "Messi", "lionelm@gmail.com");

            // start a transaction
            session.beginTransaction();

            // save the student object
            System.out.println("Saving the student ...");
            System.out.println(temStudent);
            session.save(temStudent);

            // commit transaction
            session.getTransaction().commit();

            // find out the student using id
            System.out.println("Saved student, generated id: " + temStudent.getId());


            // get a new session
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on id
            System.out.println("\nGetting student with id: " + temStudent.getId());
            Student theStudent = session.get(Student.class, temStudent.getId());

            System.out.println("Get complete: " + theStudent);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }

    }

}

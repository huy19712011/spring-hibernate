/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb_03_eager_vs_lazy_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class EagerLazyDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create Session factory
        SessionFactory factory
                = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Instructor.class)
                        .addAnnotatedClass(InstructorDetail.class)
                        .addAnnotatedClass(Course.class)
                        .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the instructor from db
            int id = 1;
            Instructor instructor =
                    session.get(Instructor.class, id);

            System.out.println("MyApp: Instructor: " + instructor);

            // option 1: call getter method while session is open
            // get courses for the instructor
            System.out.println("MyApp: Courses: " + instructor.getCourses());

            // commit transaction
            session.getTransaction().commit();
            System.out.println("MyApp: Done!!!");

            // close session
            session.close();
            System.out.println("The session is closed!");


            //since option 1 => this works after session is closed!
            System.out.println("MyApp: Courses: " + instructor.getCourses());

        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            if (session != null)
                session.close();
            factory.close();
        }
    }

}

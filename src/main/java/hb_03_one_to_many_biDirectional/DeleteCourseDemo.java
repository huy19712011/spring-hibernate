/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb_03_one_to_many_biDirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class DeleteCourseDemo {

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

            // get a course
            int id = 10;
            Course course = session.get(Course.class, id);

            // delete course
            System.out.println("Deleting course: " + course);
            session.delete(course);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!!!");


        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {

        }
    }

}

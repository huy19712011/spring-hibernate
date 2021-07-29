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
public class CreateCoursesDemo {

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

            // create some courses
            Course course1 = new Course("Angular");
            Course course2 = new Course("React");

            // add courses to instructor
            instructor.add(course1);
            instructor.add(course2);

            // save the courses
            session.save(course1);
            session.save(course2);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!!!");


        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            session.close();
            factory.close();
        }
    }

}

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
public class CreateInstructorDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("/hb_03_one_to_many_biDirectional/hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // create the objects
            Instructor instructor =
                    new Instructor("Susan", "Public", "susan@gmail.com");

            InstructorDetail instructorDetail=
                    new InstructorDetail("http://www.youtube.com", "Music");

            // associate the objects
            instructor.setInstructorDetail(instructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            // this also save the details object because of CascadeTpe.ALL
            System.out.println("Saving instructor: " + instructor);
            session.save(instructor);

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

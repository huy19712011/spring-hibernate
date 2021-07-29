/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1_C23_one_to_one_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class MainCreateOneToOneUni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create Session factory
        SessionFactory factory
                = new Configuration()
                        .configure("hibernate.cfg_C23_uni.xml")
                        .addAnnotatedClass(Instructor.class)
                        .addAnnotatedClass(InstructorDetail.class)
                        .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // create objects
            Instructor tempInstructor
                    = new Instructor("John", "Doe", "jdoe@gmail.com");

            InstructorDetail tempInstructorDetail
                    = new InstructorDetail("http://www.youtube", "Love to code");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            session.beginTransaction();

            // save the instructor
            // Note: this will also save the details object
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("Done!!!");


        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            factory.close();
        }
    }

}

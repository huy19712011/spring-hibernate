/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb_04_one_to_many_uni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class CreateCourseAndReviewsDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create Session factory
        SessionFactory factory
                = new Configuration()
                        .configure("hibernate.cfg_C26_uni.xml")
                        .addAnnotatedClass(Instructor.class)
                        .addAnnotatedClass(InstructorDetail.class)
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(Review.class)
                        .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // create a course
            Course course = new Course("JavaScript");

            // add some reviews
            course.addReview(new Review("comment 1"));
            course.addReview(new Review("comment 2"));
            course.addReview(new Review("comment 3"));

            // save the course, -> also save reviews
            System.out.println("Saving the course...");
            System.out.println(course);
            System.out.println(course.getReviews());
            session.save(course);


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

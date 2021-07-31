/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb_05_many_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class GetCoursesForStudent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create Session factory
        SessionFactory factory
                = new Configuration()
                        .configure("hibernate.cfg_C27_many_to_many.xml")
                        .addAnnotatedClass(Instructor.class)
                        .addAnnotatedClass(InstructorDetail.class)
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(Review.class)
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get the student from db
            int id = 2;
            Student student = session.get(Student.class, id);
            System.out.println("Loaded student: " + student);
            System.out.println("Courses: " + student.getCourses());

            // create more courses
            Course course1 = new Course("course 1");
            Course course2 = new Course("course 2");

            // add student to courses
            course1.addStudent(student);
            course2.addStudent(student);

            // save the courses
            System.out.println("Saving the courses ...");
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

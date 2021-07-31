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
public class CreateCourseAndStudentsDemo {

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

            // create a course
            Course course = new Course("Java Spring");

            // save the course, -> also save reviews
            System.out.println("\nSaving the course ...");
            session.save(course);
            System.out.println("Saved the course: " + course);

            // create the students
            Student student1 = new Student("John", "Doe", "jdoe@gmail.com");
            Student student2 = new Student("Mary", "Public", "mpublic@gmail.com");

            // add students to the course
            course.addStudent(student1);
            course.addStudent(student2);

            // save the students
            System.out.println("Saving students ...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students: " + course.getStudents());


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

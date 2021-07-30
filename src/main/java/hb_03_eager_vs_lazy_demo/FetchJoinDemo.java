/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hb_03_eager_vs_lazy_demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author huynq
 */
public class FetchJoinDemo {

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

            // option 2: Hibernate query with SQL

            // get the instructor from database
            int id = 1;

            // using HQL JOIN FETCH
            String sql = """
                         select i from Instructor i
                         join fetch i.courses
                         where i.id =: theInstructorId
                         """;

            Query<Instructor> query
                    = session.createQuery(sql, Instructor.class);
            query.setParameter("theInstructorId", id);
            Instructor instructor = query.getSingleResult();

            System.out.println("MyApp: Instructor: " + instructor);

            // commit transaction
            session.getTransaction().commit();
            System.out.println("MyApp: Done!!!");

            // close session
            session.close();
            System.out.println("The session is closed!");

            //since option 2 => this works after session is closed!
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

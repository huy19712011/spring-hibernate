/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1_C21_Hibernate_CRUD;

import T1_C20_Hibernate_Config_With_Annotations_Entity.Student;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class MainWithUpdateDemo {

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

/*
    //1. Update student with id = 1 -> new email
*/
            session.beginTransaction();

            int studentId = 1;

            Student student = session.get(Student.class, studentId);

            student.setEmail("newEmail@gmail.com");

            session.getTransaction().commit();

            System.out.println(student);

/*
    //2. Find students then update
*/
            // start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // query, note: Student instead of student !!!
            //  use the actual class name Student
            String sql = """
                         update Student
                         set email = 'updatedEmail'
                         where id = 2
                         """;

            int rows = session
                    .createQuery(sql)
                    .executeUpdate();

            // display students
            System.out.println("Number of rows updated: " + rows);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (HibernateException e) {

        } finally {
            factory.close();
        }

    }

}

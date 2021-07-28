/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1_C21_Hibernate_CRUD;

import T1_C20_Hibernate_Config_With_Annotations_Entity.Student;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author huynq
 */
public class MainWithQueryDemo {

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

            // start a transaction
            session.beginTransaction();

            // query, note: Student instead of student !!!
            //  use the actual class name Student
            String sql = """
                         from Student
                         """;

            String sql2 = """
                         from Student s where s.lastName='Doe'
                         """;

            List<Student> students = session
                    .createQuery(sql2)
                    .getResultList();

            // display students
            students.forEach(student -> {
                System.out.println(student);
            });

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e) {

        } finally {
            factory.close();
        }

    }

}

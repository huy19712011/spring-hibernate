/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author huynq
 */
public class TestJdbc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/web_student_tracker?useSSL=false&serverTimezone=UTC";
        String user = "admin";
        String password = "123456";

        try {

            System.out.println("Connecting to database " + url);

            Connection myConn =
                    DriverManager.getConnection(url, user, password);

            System.out.println("Connection successful!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

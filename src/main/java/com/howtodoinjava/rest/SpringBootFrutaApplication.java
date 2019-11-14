package com.howtodoinjava.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication 
public class SpringBootFrutaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFrutaApplication.class, args);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/frutapp?serverTimezone=UTC", "android", "frutapp");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from test");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

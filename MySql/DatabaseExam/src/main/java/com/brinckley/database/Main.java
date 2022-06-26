package com.brinckley.database;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {

        try {

            Connection connection;

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if (!connection.isClosed()) {
                System.out.println("Connected!");
            }

            connection.close();

        } catch (SQLException e) {
            System.out.println("Unable to connect to database!");
            e.printStackTrace();
        } finally {
            System.out.println("Finally block....");
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            //System.out.println(connection.isClosed());

            //statement.execute("INSERT INTO users(name, age, email) VALUES ('Gimli', 262, 'Gimli@valinor.com')");

            //int res = statement.executeUpdate("UPDATE users SET age=261 WHERE id=4");
            //System.out.println(res);

            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            //System.out.println(resultSet);

            statement.addBatch("INSERT INTO users(name, age, email) VALUES ('Melkor', 0, 'Melkor@valinor.com')");
            statement.addBatch("INSERT INTO users(name, age, email) VALUES ('Aragorn', 122, 'Aragorn@valinor.com')");
            statement.addBatch("INSERT INTO users(name, age, email) VALUES ('Faramir', 120, 'Faramir@valinor.com')");
            statement.executeBatch();
            statement.clearBatch();

            System.out.println(statement.isClosed());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

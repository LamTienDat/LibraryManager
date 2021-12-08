package com.example.librarymanage;

import java.sql.*;

public class DBConnect {
    private static final String url = "jdbc:mysql://localhost:3307/library";

    private static Connection con;
    private static Statement statement;

    public static void load () {
        try {
            con = DriverManager.getConnection(url, "root", "");
            statement = con.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static ResultSet executeQuery (String query) {
        try {
            ResultSet res = statement.executeQuery(query);
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public static void executeUpdate(String query){
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

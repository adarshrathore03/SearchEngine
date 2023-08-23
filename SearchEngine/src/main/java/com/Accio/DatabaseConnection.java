package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        String user = "root";
        String pw = "1234@Mysql";
        String db = "searchengineapp";
        return getConnection(user, pw, db);

    }

    private static Connection getConnection(String user, String pw, String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//com.mysql.cj.jdbc.Driver
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + pw);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}

package Moduuli7.tehtava_7_2.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MariaDbConnection {

    private static Connection conn =  null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String url = "jdbc:mariadb://localhost:3306/currency_db";
                String user = "appuser";
                String password = "salasana123";

                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Yhteys onnistui!");
            } catch (SQLException e) {
                System.err.println("Virhe: " + e.getMessage());
            }
        }
    return conn;
}

public static void terminate() {
    try {
        if (conn != null) {
            conn.close();
            System.out.println("Yhteys suljettu.");
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}


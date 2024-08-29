package com.revature.planetarium.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public class DatabaseConnector {

    /*public static Connection getConnection() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        String url = System.getenv("PLANETARIUM");
        return DriverManager.getConnection(url, config.toProperties());
    }*/

    public static Connection getConnection() throws SQLException{
        Properties properties = new Properties();

        //todo replace hard values with system environment variables
        properties.setProperty("user", System.getenv("PLANETARIUM_USERNAME"));
        properties.setProperty("password", System.getenv("PLANETARIUM_PASSWORD"));

        String url = System.getenv("PLANETARIUM_URL");

        return DriverManager.getConnection(url, properties);
    }
}

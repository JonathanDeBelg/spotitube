package nl.han.dea.persistence;

import javax.persistence.PersistenceException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/spotitube?useSSL=false";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "";
    public static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Properties properties;

    public ConnectionFactory() {
        System.out.println("Driver:" + properties.getProperty("db.driver"));
        loadDriver();
        properties = getProperties();
    }

    private Properties getProperties(){
        Properties properties = new Properties();
        String propertiesPath = getClass()
                .getClassLoader()
                .getResource("")
                .getPath() + "database.properties";
        try {
            FileInputStream fileInputStream = new FileInputStream(propertiesPath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            properties.setProperty("db.url", CONNECTION_URL);
            properties.setProperty("db.user", DB_USER);
            properties.setProperty("db.pass", DB_PASS);

            e.printStackTrace();
        }

        return properties;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.pass"));
        } catch (SQLException e) {
            throw new SpotitubePersistenceException(e);
        }
    }

    private void loadDriver() {
        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
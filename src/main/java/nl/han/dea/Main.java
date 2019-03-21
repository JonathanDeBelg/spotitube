package nl.han.dea;

import nl.han.dea.dto.PlaylistsDTO;

import java.sql.*;
import java.util.UUID;

public class Main {

    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/spotitube?useSSL=false";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "";
    public static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
//        try {
//            Class.forName(MYSQL_JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try (
//                Connection connection = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASS);
//                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE user=?");
//        ){
//            preparedStatement.setString(1,"jona");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("user") + ": ");
//                System.out.println(resultSet.getString("password"));
//
//                //                resultSet.getString("user");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        System.out.println(UUID.randomUUID());
    }
}

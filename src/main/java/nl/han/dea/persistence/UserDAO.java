package nl.han.dea.persistence;

import nl.han.dea.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDTO getUser(String username, String password) {
        UserDTO foundUser = null;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ACCOUNT WHERE user=? AND password=?");
                ){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundUser = new UserDTO();
                foundUser.setName(resultSet.getString("name"));
                foundUser.setUser(username);
                foundUser.setPassword(password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundUser;
    }
}

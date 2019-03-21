package nl.han.dea.persistence.dao;

import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.ConnectionFactory;
import nl.han.dea.util.TokenGenerator;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class TokenDAO {
    private final int TOKENLIFETIME_IN_MIN = 120;

    public void setTokenInDatabase(TokenDTO token) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement createToken = connection.prepareStatement(
                        "INSERT INTO token(token, username, expiration_date) VALUES(?, ?, ?)");

                PreparedStatement deleteExisting = connection.prepareStatement(
                        "DELETE FROM token WHERE username = ?");
        ){
            createToken.setString(1, token.getToken());
            createToken.setString(2, token.getUser());
            createToken.setString(3, incrementedDate(TOKENLIFETIME_IN_MIN));
            deleteExisting.setString(1, token.getUser());

            deleteExisting.executeUpdate();
            createToken.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDTO validateToken(String token) {
        UserDTO userDTO = new UserDTO();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement lookupToken = connection.prepareStatement(
                        "SELECT username FROM token WHERE token = ?");
        ){
            lookupToken.setString(1, token);
            ResultSet result = lookupToken.executeQuery();

            if(!result.isBeforeFirst()) {
                return null;
            } else {
                userDTO.setUser(result.getString("username"));
                return userDTO;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String incrementedDate(int increment){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MINUTE, increment);
        timestamp = new Timestamp(cal.getTime().getTime());

        return timestamp.toString();
    }
}

package nl.han.dea.persistence.dao;

import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.ConnectionFactory;
import nl.han.dea.persistence.SpotitubePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO {
    public UserDTO getUser(String username, String password);
}

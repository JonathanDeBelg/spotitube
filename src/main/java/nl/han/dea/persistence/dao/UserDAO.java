package nl.han.dea.persistence.dao;

import nl.han.dea.dto.UserDTO;

public interface UserDAO {
    public UserDTO getUser(String username, String password);
}

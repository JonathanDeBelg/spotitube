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

public interface TokenDAO {
    public void setTokenInDatabase(TokenDTO token);

    public UserDTO validateToken(String token);

    public String incrementedDate(int increment);
}

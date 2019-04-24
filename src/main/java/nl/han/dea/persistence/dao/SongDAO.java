package nl.han.dea.persistence.dao;

import nl.han.dea.dto.SongDTO;
import nl.han.dea.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SongDAO {

    public SongDTO getSong(int trackId);
}

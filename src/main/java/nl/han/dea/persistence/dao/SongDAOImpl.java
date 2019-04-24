package nl.han.dea.persistence.dao;

import nl.han.dea.dto.SongDTO;
import nl.han.dea.persistence.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class SongDAOImpl implements SongDAO {
    @Override
    public SongDTO getSong(int trackId) {
        SongDTO songDTO = new SongDTO();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getSong = connection.prepareStatement(
                        "SELECT * FROM song WHERE track_id = ?");
        ) {
            getSong.setInt(1, trackId);

            ResultSet resultSetSong = getSong.executeQuery();

            while (resultSetSong.next()) {
                songDTO.setAlbum(resultSetSong.getString("album"));
                songDTO.setSongId(trackId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songDTO;
    }
}
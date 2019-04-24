package nl.han.dea.persistence.dao;

import nl.han.dea.dto.VideoDTO;
import nl.han.dea.persistence.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class VideoDAOImpl implements VideoDAO{
    @Override
    public VideoDTO getVideo(int trackId) {
        VideoDTO videoDTO = new VideoDTO();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getSong = connection.prepareStatement(
                        "SELECT * FROM video WHERE track_id = ?");
        ) {
            getSong.setInt(1, trackId);

            ResultSet resultSetSong = getSong.executeQuery();

            while (resultSetSong.next()) {
                videoDTO.setVideoId(trackId);
                videoDTO.setDescription(resultSetSong.getString("description"));
                videoDTO.setPublicationDate(resultSetSong.getString("publicationdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videoDTO;
    }
}

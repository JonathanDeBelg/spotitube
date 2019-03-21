package nl.han.dea.persistence.dao;

import nl.han.dea.dto.TrackDTO;
import nl.han.dea.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrackDAO {
    ArrayList<TrackDTO> tracks = new ArrayList<>();

    public ArrayList<TrackDTO> getAllTracksAvailableInAnyPlaylist() {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getTracksInAnyPlaylist = connection.prepareStatement(
                        "SELECT title, tip.playlist_id, duration FROM trackinplaylist tip INNER JOIN track t ON tip.track_id = t.id");
        ){
            ResultSet resultSet = getTracksInAnyPlaylist.executeQuery();

            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setTitle(resultSet.getString("title"));
                track.setDuration(resultSet.getInt("duration"));
                tracks.add(track);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracks;
    }

    public int overallDurationOfPlaylist() {
        ArrayList<TrackDTO> tracksInPlaylist = getAllTracksAvailableInAnyPlaylist();
        int duration = 0;

        for (TrackDTO track:tracksInPlaylist) {
            duration += track.getDuration();
        }

        return duration;
    }
}

package nl.han.dea.persistence.dao;

import nl.han.dea.dto.TrackDTO;
import nl.han.dea.persistence.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class TrackDAOImpl implements TrackDAO {
    ArrayList<TrackDTO> tracks = new ArrayList<>();

    @Override
    public ArrayList<TrackDTO> getAllTracksAvailableInAnyPlaylist() {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getTracksInAnyPlaylist = connection.prepareStatement(
                        "SELECT title, tip.playlist_id, duration FROM trackinplaylist tip INNER JOIN track t ON tip.track_id = t.id");
        ) {
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

    @Override
    public TrackDTO getOfflineAvailability(int playlistId, int trackId) {
        TrackDTO trackDTO = new TrackDTO();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getofflineAvailability = connection.prepareStatement(
                        "SELECT offlineAvailable FROM trackinplaylist WHERE track_id = ? AND playlist_id = ?");
        ) {
            getofflineAvailability.setInt(1, trackId);
            getofflineAvailability.setInt(2,playlistId);

            ResultSet resultSetOfflineAvailable = getofflineAvailability.executeQuery();

            while (resultSetOfflineAvailable.next()) {
                trackDTO.setOfflineAvailable(resultSetOfflineAvailable.getBoolean("offlineAvailable"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trackDTO;
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackID) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement deleteTrackFromPlaylist = connection.prepareStatement(
                        "DELETE FROM trackinplaylist WHERE playlist_id = ? AND track_id = ?");
        ){
            deleteTrackFromPlaylist.setInt(1, playlistId);
            deleteTrackFromPlaylist.setInt(2, trackID);

            deleteTrackFromPlaylist.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int overallDurationOfPlaylist() {
        ArrayList<TrackDTO> tracksInPlaylist = getAllTracksAvailableInAnyPlaylist();
        int duration = 0;

        for (TrackDTO track : tracksInPlaylist) {
            duration += track.getDuration();
        }

        return duration;
    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO track) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement addTrackToPlaylist = connection.prepareStatement(
                        "INSERT INTO trackinplaylist VALUES (?, ?, ?)");
        ){
            addTrackToPlaylist.setInt(1, playlistId);
            addTrackToPlaylist.setInt(2, track.getId());
            addTrackToPlaylist.setBoolean(3, track.getOfflineAvailable());

            addTrackToPlaylist.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

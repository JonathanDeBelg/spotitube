package nl.han.dea.persistence.dao;

import nl.han.dea.dto.TrackDTO;
import nl.han.dea.dto.TracksDTO;
import nl.han.dea.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TracksDAO {
    TracksDTO tracksDTO = new TracksDTO();
    ArrayList<TrackDTO> tracks = new ArrayList<>();

    public TracksDTO getAllTracks() {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getAllTracks = connection.prepareStatement(
                        "SELECT * FROM track t");
        ){
            ResultSet resultSet = getAllTracks.executeQuery();

            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlayCount(resultSet.getInt("playCount"));
                track.setPublicationDate(resultSet.getString("publicationDate"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
                tracks.add(track);
            }
            tracksDTO.setTracks(tracks);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracksDTO;
    }

    public TracksDTO getTracksInPLaylist() {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getAllTracks = connection.prepareStatement(
                        "SELECT * FROM track t INNER JOIN trackinplaylist tip ON t.id = tip.track_id");
        ){
            ResultSet resultSet = getAllTracks.executeQuery();

            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlayCount(resultSet.getInt("playCount"));
                track.setPublicationDate(resultSet.getString("publicationDate"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
                tracks.add(track);
            }

            tracksDTO.setTracks(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracksDTO;
    }

    public TracksDTO getTracksBelongingToPlaylist(int playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getTracksInPlaylist = connection.prepareStatement(
                        "SELECT * FROM track t INNER JOIN trackinplaylist tip ON t.id = tip.track_id WHERE tip.playlist_id = ?");
        ){

            getTracksInPlaylist.setInt(1, playlistId);
            ResultSet resultSet = getTracksInPlaylist.executeQuery();

            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlayCount(resultSet.getInt("playCount"));
                track.setPublicationDate(resultSet.getString("publicationDate"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
                tracks.add(track);
            }

            tracksDTO.setTracks(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracksDTO;
    }

}

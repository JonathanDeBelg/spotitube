package nl.han.dea.persistence.dao;

import nl.han.dea.dto.PlaylistDTO;
import nl.han.dea.dto.PlaylistsDTO;
import nl.han.dea.dto.TrackDTO;
import nl.han.dea.persistence.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {

    public void deletePlaylist(int playlistId) {

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();

        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement deleteGivenPlaylist = connection.prepareStatement(
                        "DELETE FROM playlist WHERE id =?");
        ){
            deleteGivenPlaylist.setInt(1, playlistId);

            deleteGivenPlaylist.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlaylist(PlaylistDTO playlist) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement addPlaylist = connection.prepareStatement(
                        "INSERT INTO playlist(id, name, owner) VALUES (NULL, ?, ?)");
        ){
            addPlaylist.setString(1, playlist.getName());
            addPlaylist.setBoolean(2, playlist.getOwner());

            System.out.println(addPlaylist);
            addPlaylist.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifyPlaylist(int playlistId, PlaylistDTO playlist) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement modifyGivenPlaylist = connection.prepareStatement(
                        "UPDATE playlist SET name = ? WHERE id = ?");
        ){
            modifyGivenPlaylist.setString(1, playlist.getName());
            modifyGivenPlaylist.setInt(2, playlistId);

            modifyGivenPlaylist.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void addTrackToPlaylist(int playlistId, TrackDTO track) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement addTrackToPlaylist = connection.prepareStatement(
                        "INSERT INTO trackinplaylist VALUES (?, ?)");
                PreparedStatement updateOfflineAvailabilityOnChange = connection.prepareStatement(
                        "UPDATE track SET offlineAvailable = ? WHERE id = ?");
        ){
            addTrackToPlaylist.setInt(1, playlistId);
            addTrackToPlaylist.setInt(2, track.getId());

            updateOfflineAvailabilityOnChange.setBoolean(1, track.getOfflineAvailable());
            updateOfflineAvailabilityOnChange.setInt(2, track.getId());

            addTrackToPlaylist.executeUpdate();
            updateOfflineAvailabilityOnChange.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

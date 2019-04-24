package nl.han.dea.persistence.dao;

import nl.han.dea.dto.PlaylistDTO;
import nl.han.dea.dto.PlaylistsDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class PlaylistDAOImpl implements PlaylistDAO{
    @Override
    public void deletePlaylist(int playlistId) {

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();

        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement deletePlaylistOwner= connection.prepareStatement(
                        "DELETE FROM playlistowner WHERE playlist_id =?");
                PreparedStatement deleteGivenPlaylist = connection.prepareStatement(
                        "DELETE FROM playlist WHERE id =?");
                PreparedStatement deleteTracksInPlaylist = connection.prepareStatement(
                        "DELETE FROM trackinplaylist WHERE playlist_id =?");
        ){
            deleteTracksInPlaylist.setInt(1, playlistId);
            deletePlaylistOwner.setInt(1, playlistId);
            deleteGivenPlaylist.setInt(1, playlistId);

            deletePlaylistOwner.executeUpdate();
            deleteGivenPlaylist.executeUpdate();
            deleteTracksInPlaylist.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPlaylist(UserDTO user, PlaylistDTO playlist) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement addPlaylist = connection.prepareStatement(
                        "INSERT INTO playlist(id, name, owner) VALUES (NULL, ?, ?)");
        ){
            addPlaylist.setString(1, playlist.getName());
            addPlaylist.setString(2, user.getUser());

            addPlaylist.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public PlaylistDTO getPlaylist(UserDTO user, int playlistId){
        PlaylistDTO playlistDTO = new PlaylistDTO();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getPlaylist = connection.prepareStatement(
                        "SELECT * FROM playlist WHERE id = ?");
        ){
            getPlaylist.setInt(1, playlistId);
            ResultSet resultSet = getPlaylist.executeQuery();

            while(resultSet.next()) {
                playlistDTO.setId(resultSet.getInt("id"));
                if (user.getUser().equals(resultSet.getString("owner"))) {
                    playlistDTO.setOwner(true);
                } else {
                    playlistDTO.setOwner(false);
                }
                playlistDTO.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlistDTO;
    }
}

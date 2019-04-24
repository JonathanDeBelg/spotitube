package nl.han.dea.persistence.dao;

import nl.han.dea.dto.PlaylistDTO;
import nl.han.dea.dto.PlaylistsDTO;
import nl.han.dea.dto.TrackDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class PlaylistsDAOImpl implements PlaylistsDAO {
    PlaylistsDTO playlistsDTO = new PlaylistsDTO();
    TrackDAO trackDAO = new TrackDAOImpl();

    @Override
    public PlaylistsDTO getPlaylists(UserDTO user) {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();

        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getPlaylist = connection.prepareStatement(
                        "SELECT p.id, p.name, p.owner FROM playlist p");
                PreparedStatement getTracksInPlaylists = connection.prepareStatement(
                        "SELECT title, tip.playlist_id FROM trackinplaylist tip INNER JOIN track t ON tip.track_id = t.id");
        ){
            ResultSet resultSet = getPlaylist.executeQuery();
            ResultSet resultSetTracksInPlaylist = getTracksInPlaylists.executeQuery();

            while (resultSet.next()) {
                ArrayList<TrackDTO> tracks = new ArrayList<>();

                PlaylistDTO playlist = new PlaylistDTO();
                playlist.setId(resultSet.getInt("p.id"));
                playlist.setName(resultSet.getString("p.name"));

                if (user.getUser().equals(resultSet.getString("owner"))) {
                    playlist.setOwner(true);
                } else {
                    playlist.setOwner(false);
                }

                playlist.setTracks(tracks);
                playlists.add(playlist);
            }

            playlistsDTO.setPlaylists(playlists);
            playlistsDTO.setLength(trackDAO.overallDurationOfPlaylist());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlistsDTO;
    }
}

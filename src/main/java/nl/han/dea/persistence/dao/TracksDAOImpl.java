package nl.han.dea.persistence.dao;

import nl.han.dea.dto.SongDTO;
import nl.han.dea.dto.TrackDTO;
import nl.han.dea.dto.TracksDTO;
import nl.han.dea.dto.VideoDTO;
import nl.han.dea.persistence.ConnectionFactory;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class TracksDAOImpl implements TracksDAO {
    TracksDTO tracksDTO = new TracksDTO();
    ArrayList<TrackDTO> tracks = new ArrayList<>();
    TrackDAO trackDAO = new TrackDAOImpl();
    SongDAO songDAO = new SongDAOImpl();
    VideoDAO videoDAO = new VideoDAOImpl();

    @Override
    public TracksDTO getAllTracks() {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getTracks = connection.prepareStatement(
                        "SELECT * FROM track");
        ){

            ResultSet resultSet = getTracks.executeQuery();
            ResultSet resultSetTrackDefinition;

            while (resultSet.next()) {
                int track_id = resultSet.getInt("id");
                TrackDTO track = new TrackDTO();

                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setPlayCount(resultSet.getInt("playCount"));

                if (resultSet.getInt("track_type") == 0) {
                    SongDTO song = songDAO.getSong(track_id);

                    track.setAlbum(song.getAlbum());
                    track.setDescription("");
                    track.setPublicationDate("");
                } else {
                    VideoDTO video = videoDAO.getVideo(track_id);

                    track.setDescription(video.getDescription());
                    track.setPublicationDate(video.getPublicationDate());
                    track.setAlbum("");
                }

                tracks.add(track);
            }

            tracksDTO.setTracks(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracksDTO;
    }

    @Override
    public TracksDTO getAllTracksExceptInPlaylist(int playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getTracksExceptInPLaylist = connection.prepareStatement(
                        "SELECT T.* FROM track T LEFT OUTER JOIN (SELECT * FROM track T1 INNER JOIN trackinplaylist TIP1 ON T1.id = TIP1.track_id " +
                                "WHERE playlist_id =?) AS TIP ON T.id = TIP.track_id WHERE TIP.id IS NULL");
        ){
            getTracksExceptInPLaylist.setInt(1, playlistId);
            ResultSet resultSet = getTracksExceptInPLaylist.executeQuery();

            while (resultSet.next()) {
                int track_id = resultSet.getInt("id");
                TrackDTO track = new TrackDTO();

                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setPlayCount(resultSet.getInt("playCount"));

                if (resultSet.getInt("track_type") == 0) {
                    SongDTO song = songDAO.getSong(track_id);

                    track.setAlbum(song.getAlbum());
                    track.setDescription("");
                    track.setPublicationDate("");
                } else {
                    VideoDTO video = videoDAO.getVideo(track_id);

                    track.setDescription(video.getDescription());
                    track.setPublicationDate(video.getDescription());
                    track.setAlbum("");
                }

                tracks.add(track);
            }

            tracksDTO.setTracks(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracksDTO;
    }

    @Override
    public TracksDTO getTracksInPlaylist() {
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

    @Override
    public TracksDTO getTracksBelongingToPlaylist(int playlistId) {
        int track_id;

        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement getTracksInPlaylist = connection.prepareStatement(
                        "SELECT * FROM track t INNER JOIN trackinplaylist tip ON t.id = tip.track_id WHERE tip.playlist_id = ?");
        ){
            getTracksInPlaylist.setInt(1, playlistId);
            ResultSet resultSet = getTracksInPlaylist.executeQuery();

            while (resultSet.next()) {
                track_id = resultSet.getInt("id");
                TrackDTO track = new TrackDTO();

                TrackDTO trackOffline = trackDAO.getOfflineAvailability(playlistId, track_id);

                if (resultSet.getInt("track_type") == 0) {
                    SongDTO song = songDAO.getSong(track_id);

                    track.setAlbum(song.getAlbum());
                    track.setDescription(null);
                    track.setPublicationDate(null);
                } else {
                    VideoDTO video = videoDAO.getVideo(track_id);

                    track.setDescription(video.getDescription());
                    track.setPublicationDate(video.getPublicationDate());
                    track.setAlbum(null);
                }

                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setPlayCount(resultSet.getInt("playCount"));

                track.setOfflineAvailable(trackOffline.getOfflineAvailable());

                tracks.add(track);
            }

            tracksDTO.setTracks(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tracksDTO;
    }
}

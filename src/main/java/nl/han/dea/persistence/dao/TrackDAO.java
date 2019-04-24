package nl.han.dea.persistence.dao;

import nl.han.dea.dto.TrackDTO;
import java.util.ArrayList;

public interface TrackDAO {
    public ArrayList<TrackDTO> getAllTracksAvailableInAnyPlaylist();

    public TrackDTO getOfflineAvailability(int playlistId, int trackId);

    public void deleteTrackFromPlaylist(int playlistId, int trackID);

    public int overallDurationOfPlaylist();

    public void addTrackToPlaylist(int playlistId, TrackDTO track);
}
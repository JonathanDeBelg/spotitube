package nl.han.dea.persistence.dao;

import nl.han.dea.dto.TracksDTO;

public interface TracksDAO {
    public TracksDTO getAllTracks();

    public TracksDTO getAllTracksExceptInPlaylist(int playlistId);

    public TracksDTO getTracksInPlaylist();

    public TracksDTO getTracksBelongingToPlaylist(int playlistId);

}

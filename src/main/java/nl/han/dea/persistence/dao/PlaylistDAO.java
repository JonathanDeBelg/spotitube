package nl.han.dea.persistence.dao;

import nl.han.dea.dto.PlaylistDTO;
import nl.han.dea.dto.UserDTO;

public interface PlaylistDAO {

    public void deletePlaylist(int playlistId);

    public void addPlaylist(UserDTO user, PlaylistDTO playlist);

    public void modifyPlaylist(int playlistId, PlaylistDTO playlist);

    public PlaylistDTO getPlaylist(UserDTO user, int playlistId);
}

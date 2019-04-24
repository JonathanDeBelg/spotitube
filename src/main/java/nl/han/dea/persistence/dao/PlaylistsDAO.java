package nl.han.dea.persistence.dao;

import nl.han.dea.dto.PlaylistsDTO;
import nl.han.dea.dto.UserDTO;

public interface PlaylistsDAO {

    public PlaylistsDTO getPlaylists(UserDTO user);
}
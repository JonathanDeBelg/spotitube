package nl.han.dea;

import nl.han.dea.dto.*;
import nl.han.dea.persistence.dao.PlaylistDAO;
import nl.han.dea.persistence.dao.PlaylistsDAO;
import nl.han.dea.persistence.dao.TrackDAO;
import nl.han.dea.persistence.dao.TracksDAO;
import nl.han.dea.resources.PlaylistResource;
import nl.han.dea.service.TokenValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaylistResourceTest {

    @Mock
    private TokenValidationService tokenValidationService;

    @Mock
    private PlaylistsDAO playlistsDAOMock;

    @Mock
    private PlaylistDAO playlistDAOMock;

    @Mock
    private TracksDAO tracksDAOMock;

    @Mock
    private TrackDAO trackDAOMock;

    @InjectMocks
    private PlaylistResource sut;

    @Test
    void getAllPlaylistsTestSucces(){
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        playlists.add(new PlaylistDTO(1, "Testplaylist", false));

        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.setPlaylists(playlists);

        UserDTO userDTO = new UserDTO("TestUser", "TestPW", "TestName");
        when(tokenValidationService.validateToken(any()))
                .thenReturn(userDTO);
        when(playlistsDAOMock.getPlaylists(userDTO))
                .thenReturn(playlistsDTO);

        Response actualResult = sut.getAllPlaylists("1234");
        assertEquals(Status.OK.getStatusCode(), actualResult.getStatus());


        PlaylistsDTO actualPlaylist = (PlaylistsDTO) actualResult.getEntity();
        assertEquals(playlistsDTO, actualPlaylist);
    }

    @Test
    void deletePlaylistTestSucces() {
        sut.deletePlaylist(1, "1234");
        verify(playlistDAOMock).deletePlaylist(1);
    }

    @Test
    void modifyPlaylistTestSucces() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        sut.modifyPlaylist(1, playlistDTO, "1234");
        verify(playlistDAOMock).modifyPlaylist(1, playlistDTO);
    }

    @Test
    void addPlaylistTestSucces() {
        PlaylistDTO playlistDTO = new PlaylistDTO(1, "Test", true);
        UserDTO userDTO = new UserDTO("UserTest", "PassTest", "NameTest");
        TokenDTO tokenDTO = new TokenDTO();

        sut.addPlaylist(playlistDTO, "1234");
        verify(playlistDAOMock).addPlaylist(userDTO, playlistDTO);
    }

        @Test
    void removeTracksFromPlaylistTestSucces() {
        TrackDTO trackDTO = new TrackDTO();
        sut.deleteTrackFromPlaylist(1, trackDTO.getId(), "1234");
        verify(trackDAOMock).deleteTrackFromPlaylist(1, trackDTO.getId());
    }

    @Test
    void addTrackToPlaylistTestSucces() {
        TrackDTO trackDTO = new TrackDTO();
        sut.addTrackToPlaylist(1, trackDTO, "1234");
        verify(trackDAOMock).addTrackToPlaylist(1, trackDTO);
    }

    @Test
    void getTracksBelongingToPlaylistTestSucces() {
        sut.getTracksBelongingToPlaylist(1, "String");
        verify(tracksDAOMock).getTracksBelongingToPlaylist(1);
    }
}

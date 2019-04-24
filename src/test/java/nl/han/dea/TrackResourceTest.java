package nl.han.dea;

import nl.han.dea.dto.*;
import nl.han.dea.persistence.dao.TrackDAO;
import nl.han.dea.persistence.dao.TracksDAO;
import nl.han.dea.resources.TrackResource;
import nl.han.dea.service.TokenValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TrackResourceTest {
    @Mock
    private TokenValidationService tokenValidationService;

    @Mock
    private TracksDAO tracksDAO;

    @Mock
    private TrackDAO trackDAO;

    @InjectMocks
    private TrackResource sut;

    @Test
    public void getAllTracksTestSucces() {
        sut.getTracks("1234");
        verify(tracksDAO).getAllTracks();
    }

    @Test
    void getAllTracksNotInPlaylistTestSucces() {
        PlaylistDTO playlistDTO = new PlaylistDTO(1, "Test", true);
        UserDTO userDTO = new UserDTO();

        sut.getTracks("1234", playlistDTO.getId());
        verify(tracksDAO).getAllTracksExceptInPlaylist(playlistDTO.getId());
    }
}

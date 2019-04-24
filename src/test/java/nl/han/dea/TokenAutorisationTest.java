package nl.han.dea;

import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.dao.TokenDAO;
import nl.han.dea.persistence.dao.TokenDAOImpl;
import nl.han.dea.resources.LoginResource;
import nl.han.dea.resources.PlaylistResource;
import nl.han.dea.service.AuthenticationService;
import nl.han.dea.service.SpotitubeTokenValidationException;
import nl.han.dea.service.TokenValidationService;
import nl.han.dea.service.TokenValidationServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito .ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenAutorisationTest {
    @Mock
    private TokenDAOImpl sut;

    @InjectMocks
    private TokenValidationServiceImpl tokenValidationService;

    @Test
    void TokenValidationSuccess(){
        UserDTO userDTO = new UserDTO("Testuser", "Testpass", "Testname");
        when(sut.validateToken(any()))
                .thenReturn(userDTO);

        UserDTO actualResult = tokenValidationService.validateToken("1234");

        assertEquals(actualResult, userDTO);
    }

    @Test
    void TokenValidationFailure(){
        UserDTO userDTO = new UserDTO("Testuser", "Testpass", "Testname");
        when(sut.validateToken(any()))
                .thenThrow(new SpotitubeTokenValidationException("Token doesn't exist"));

        SpotitubeTokenValidationException spotitubeTokenValidationException = assertThrows(SpotitubeTokenValidationException.class, () -> {
            UserDTO actualResult = sut.validateToken("");
        });

        assertEquals("Token doesn't exist", spotitubeTokenValidationException.getMessage());
    }
}

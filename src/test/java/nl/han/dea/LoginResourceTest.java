package nl.han.dea;

import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.resources.LoginResource;
import nl.han.dea.service.AuthenticationService;
import nl.han.dea.service.ExceptionCause;
import nl.han.dea.service.SpotitubeLoginException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito .ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginResourceTest {
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private LoginResource sut;

    @Test
    void loginSuccess() {
        when(authenticationService.login("jona", "Jonapass"))
                .thenReturn(new TokenDTO("1234", "Testuser"));

        UserDTO userDTO = new UserDTO("jona", "Jonapass", "Jonathan");
        Response actualResult = sut.login(userDTO);

        assertEquals(Status.OK.getStatusCode(), actualResult.getStatus());

        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
        assertEquals("Testuser", actualToken.getUser());
        assertEquals("1234", actualToken.getToken());
    }

    @Test
    void loginFailureWithWrongValues() {
        when(authenticationService.login(anyString(), anyString()))
                .thenThrow(new SpotitubeLoginException("Login failed for user.", ExceptionCause.WRONG_USERNAME_PASSWORD));
        UserDTO userDTO = new UserDTO("jona", "wrongpass", "Jonathan");
        SpotitubeLoginException spotitubeLoginException = assertThrows(SpotitubeLoginException.class, () -> {
            Response actualResult = sut.login(new UserDTO("jona", "wrongpass", "Jonathan"));
        });

        assertEquals("Login failed for user.", spotitubeLoginException.getMessage());
    }

    @Test
    void loginFailureWithEmptyFields() {
        when(authenticationService.login(anyString(), anyString()))
                .thenThrow(new SpotitubeLoginException("Missing input for a field", ExceptionCause.MISSING_INPUT));
        UserDTO userDTO = new UserDTO("jona", "Jonapass", "Jonathan");
        SpotitubeLoginException spotitubeLoginException = assertThrows(SpotitubeLoginException.class, () -> {
            Response actualResult = sut.login(new UserDTO("jona", "", "Jonathan"));
        });

        assertEquals("Missing input for a field", spotitubeLoginException.getMessage());
    }
}
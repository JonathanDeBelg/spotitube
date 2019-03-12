package nl.han.dea;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.resources.LoginResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResourceTest {
    private LoginResource sut;

    @BeforeEach
    public void setUp() {
        sut = new LoginResource();
    }

    @Test
    void loginSucces() {
        UserDTO userDTO = new UserDTO("Zuen", "1234");
        Response actualResult = sut.login(userDTO);
        assertEquals(Status.OK.getStatusCode(), actualResult.getStatus());
        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
        assertEquals("Erik warnar", actualToken.getUser());
        assertEquals("123-123", actualToken.getToken());
    }

//    @Test
//    void loginFailure() {
//        UserDTO userDTO = new UserDTO("Zuen", "1234");
//        Response actualResult = sut.login(userDTO);
//        assertEquals(Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());
//
//        ErrorDTO actualError = (ErrorDTO) actualResult.getEntity();
//        assertEquals("Login for user Zuen failed ", actualError.getMessage);
//    }
}

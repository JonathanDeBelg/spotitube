package nl.han.dea;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.dao.UserDAO;
import nl.han.dea.resources.LoginResource;
import nl.han.dea.util.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LoginResourceTest {
    @Mock
    private UserDAO UserDAOStub;

    @Mock
    private TokenGenerator tokenGeneratorMock;

    @InjectMocks
    private LoginResource sut;

//    @BeforeEach
//    public void setUp() {
//        sut = new LoginResource();
//    }

    @Test
    void loginSucces() {
        Mockito.when(tokenGeneratorMock.generateToken())
                .thenReturn("1234");
        UserDTO mockedUser = new UserDTO();
        mockedUser.setName("Test Testuser");
        mockedUser.setPassword("testpassword");
        mockedUser.setUser("testuser");
        Mockito.when(UserDAOStub.getUser("Test Testuser", "testpassword")).thenReturn(mockedUser);

        UserDTO userDTO = new UserDTO("Zuen", "1234", "Zuen");
        Response actualResult = sut.login(userDTO);

        assertEquals(Status.OK.getStatusCode(), actualResult.getStatus());

        TokenDTO actualToken = (TokenDTO) actualResult.getEntity();
        assertEquals("Test Testuser", actualToken.getUser());
        assertEquals("1234", actualToken.getToken());
    }

    @Test
    void loginFailureWithWrongInput() {
        UserDTO userDTO = new UserDTO("Zue", "1234", "Zuen");
        Response actualResult = sut.login(userDTO);
        assertEquals(Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO actualError = (ErrorDTO) actualResult.getEntity();
        assertEquals("Login for user Zue failed.", actualError.getMessage());
    }

    @Test
    void loginFailureWithNoUserSpecified() {
        UserDTO userDTO = new UserDTO("", "1234", "Zuen");
        Response actualResult = sut.login(userDTO);
        assertEquals(Status.BAD_REQUEST.getStatusCode(), actualResult.getStatus());

        ErrorDTO actualError = (ErrorDTO) actualResult.getEntity();
        assertEquals("A value is missing in the fields.", actualError.getMessage());
    }

    @Test
    void loginFailureWithNoPasswordSpecified() {
        UserDTO userDTO = new UserDTO("Zuen", "", "Zuen");
        Response actualResult = sut.login(userDTO);
        assertEquals(Status.BAD_REQUEST.getStatusCode(), actualResult.getStatus());

        ErrorDTO actualError = (ErrorDTO) actualResult.getEntity();
        assertEquals("A value is missing in the fields.", actualError.getMessage());
    }
}

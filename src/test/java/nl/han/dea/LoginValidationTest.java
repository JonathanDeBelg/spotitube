package nl.han.dea;

import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.dao.TokenDAOImpl;
import nl.han.dea.persistence.dao.UserDAO;
import nl.han.dea.persistence.dao.UserDAOImpl;
import nl.han.dea.resources.LoginResource;
import nl.han.dea.service.AuthenticationService;
import nl.han.dea.service.AuthenticationServiceImpl;
import nl.han.dea.service.TokenValidationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginValidationTest {
    @Mock
    private UserDAOImpl sut;

    @Mock
    private TokenDAOImpl tokenDAOImpl;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

//    @Test
//    void LoginAuthenticationSuccess(){
//        TokenDTO tokenDTO = new TokenDTO("1234", "TestUser");
//
//        when(tokenDAOImpl.setTokenInDatabase(tokenDTO))
//                .thenReturn()
//
//        when(sut.getUser("Testuser", "Testpass"))
//                .thenReturn(new UserDTO("Testuser", "Testpass", "Testname"));
//        when(authenticationService.login("Testuser", "Testpass"))
//                .thenReturn(tokenDTO);
//
//        UserDTO actualResult = sut.getUser("Testuser", "Testpass");
//
//        assertEquals(tokenDTO, actualResult);
//    }
}
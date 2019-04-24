package nl.han.dea;

import nl.han.dea.exceptionmapper.TokenAuthenticationExceptionMapper;
import nl.han.dea.service.SpotitubeTokenValidationException;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenExceptionMapperTest {
    private TokenAuthenticationExceptionMapper sut;

    @Before
    void setup() {
        sut = new TokenAuthenticationExceptionMapper();
    }

    @Test
    void returnsStatus401WhenSpotitubeLoginExceptionIsThrowOnTokenValidation() {
        sut = new TokenAuthenticationExceptionMapper();
        SpotitubeTokenValidationException exception = new SpotitubeTokenValidationException("message");
        Response actualResponse = sut.toResponse(exception);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResponse.getStatus());
        assertEquals("message", exception.getMessage());
    }

}

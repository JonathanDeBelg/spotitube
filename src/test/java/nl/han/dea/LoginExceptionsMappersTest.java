package nl.han.dea;

import nl.han.dea.exceptionmapper.LoginExceptionMapper;
import nl.han.dea.service.ExceptionCause;
import nl.han.dea.service.SpotitubeLoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginExceptionsMappersTest {
    private LoginExceptionMapper sut;

    @BeforeEach
    void setup(){
        sut = new LoginExceptionMapper();
    }

    @Test
    void returnsStatus400WhenSpotitubeLoginExceptionIsThrowOnLoginAuthentication() {
        SpotitubeLoginException exception = new SpotitubeLoginException("message", ExceptionCause.MISSING_INPUT);
        Response actualResponse = sut.toResponse(exception);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actualResponse.getStatus());
        assertEquals("message", exception.getMessage());
    }

    @Test
    void returnsStatus401WhenSpotitubeLoginExceptionIsThrowOnLoginAuthentication() {
        SpotitubeLoginException exception = new SpotitubeLoginException("message", ExceptionCause.WRONG_USERNAME_PASSWORD);
        Response actualResponse = sut.toResponse(exception);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResponse.getStatus());
        assertEquals("message", exception.getMessage());
    }

    @Test
    void returnsStatus500WhenSpotitubeLoginExceptionIsThrowOnLoginAuthentication() {
        SpotitubeLoginException exception = new SpotitubeLoginException("message", null);
        Response actualResponse = sut.toResponse(exception);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), actualResponse.getStatus());
        assertEquals("message", exception.getMessage());
    }
}

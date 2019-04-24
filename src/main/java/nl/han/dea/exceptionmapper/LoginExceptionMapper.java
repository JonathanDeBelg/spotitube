package nl.han.dea.exceptionmapper;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.service.SpotitubeLoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static nl.han.dea.service.ExceptionCause.MISSING_INPUT;
import static nl.han.dea.service.ExceptionCause.WRONG_USERNAME_PASSWORD;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<SpotitubeLoginException> {
    @Override
    public Response toResponse(SpotitubeLoginException exception) {
        Response response;

        if (exception.getExceptionCause() == MISSING_INPUT) {
            response = Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDTO(exception.getMessage()))
                    .build();
        } else if (exception.getExceptionCause() == WRONG_USERNAME_PASSWORD) {
            response = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO(exception.getMessage()))
                    .build();
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorDTO(exception.getMessage()))
                    .build();
        }

        return response;
    }
}
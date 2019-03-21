package nl.han.dea.exceptionmapper;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.service.SpotitubeLoginException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LoginExceptionMapper implements ExceptionMapper<SpotitubeLoginException> {
    @Override
    public Response toResponse(SpotitubeLoginException exception) {
        switch (exception.getExceptionCause()) {
            case MISSING_INPUT:
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDTO(exception.getMessage()))
                    .build();

            case WRONG_USERNAME_PASSWORD:
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ErrorDTO(exception.getMessage()))
                        .build();
        }
        return null;
    }
}
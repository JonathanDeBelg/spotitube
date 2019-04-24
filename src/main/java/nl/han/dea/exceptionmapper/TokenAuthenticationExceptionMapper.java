package nl.han.dea.exceptionmapper;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.service.SpotitubeTokenValidationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TokenAuthenticationExceptionMapper implements ExceptionMapper<SpotitubeTokenValidationException> {
    @Override
    public Response toResponse(SpotitubeTokenValidationException e) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorDTO(e.getMessage()))
                .build();
    }
}

package nl.han.dea.resources;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.SpotitubePersistenceException;
import nl.han.dea.persistence.dao.TokenDAO;
import nl.han.dea.persistence.dao.UserDAO;
import nl.han.dea.service.AuthenticationService;
import nl.han.dea.util.TokenGenerator;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {
    private AuthenticationService authenticationService;

    public LoginResource() {
    }

    @Inject
    public LoginResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        TokenDTO token = authenticationService.login(user.getUser(), user.getPassword());
        return Response.ok(token).build();
    }
}
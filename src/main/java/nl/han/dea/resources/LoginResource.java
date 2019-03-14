package nl.han.dea.resources;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {
    private UserDAO userDAO = new UserDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        UserDTO authenticatedUser = userDAO.getUser(user.getUser(), user.getPassword());

        if (user.getUser().isEmpty() || user.getPassword().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDTO("A value is missing in the fields."))
                    .build();
        } else if (authenticatedUser != null) {
            return Response.ok().entity(new TokenDTO("123-123", authenticatedUser.getName())).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("Login for user " + user.getUser() + " failed."))
                    .build();
        }
    }
}
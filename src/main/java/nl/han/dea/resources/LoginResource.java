package nl.han.dea.resources;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.dto.TokenDTO;
import nl.han.dea.dto.UserDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {

        if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDTO("A value is missing in the fields."))
                    .build();
        } else if (user.getName().equals("Zuen") && user.getPassword().equals("1234")) {
            return Response.ok()
                    .entity(new TokenDTO("123-123", "Erik warnar"))
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("Login for user " + user.getName() + " failed."))
                    .build();
        }
    }
}
package nl.han.dea;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        return Response.ok().entity(new TokenDTO("123-123", "Erik warnar")).build();
    }
}
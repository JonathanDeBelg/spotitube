package nl.han.dea.resources;

import nl.han.dea.dto.PlaylistsDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists() {
        return Response.ok().entity(new PlaylistsDTO(15332)).build();
    }
}

package nl.han.dea.resources;

import nl.han.dea.dto.ErrorDTO;
import nl.han.dea.dto.PlaylistDTO;
import nl.han.dea.dto.PlaylistsDTO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/playlists")
public class PlaylistResource {
    private PlaylistsDTO playlists;

    public PlaylistResource() {
        this.playlists =  new PlaylistsDTO(15332);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists() {
        return Response.ok().entity(playlists).build();
    }

    @DELETE
    @Path("/:{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id) {
        playlists.deletePlaylist(id);
        return Response.ok().entity(playlists).build();

    }
}

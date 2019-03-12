package nl.han.dea;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/playlists")
public class PlaylistResource {
    private ArrayList<PlaylistDTO> playlists = new ArrayList<>();

    public PlaylistResource() {
        playlists.add( new PlaylistDTO(1, "Elemenatory", true, 1230));
        playlists.add( new PlaylistDTO(2, "Musketiers 2", false, 23498));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists() {
//        return Response.ok(playlists.values()).build();
    }
}

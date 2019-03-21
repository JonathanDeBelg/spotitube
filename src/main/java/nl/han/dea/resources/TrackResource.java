package nl.han.dea.resources;

import nl.han.dea.persistence.dao.TrackDAO;
import nl.han.dea.persistence.dao.TracksDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {
    TracksDAO tracksDAO = new TracksDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("forPlaylist") boolean forPlaylist){
        if (forPlaylist == true) {
            return Response.ok().entity(tracksDAO.getTracksInPLaylist()).build();
        } else if (forPlaylist == false) {
            return Response.ok().entity(tracksDAO.getAllTracks()).build();
        }
        return Response.ok().entity(tracksDAO.getAllTracks()).build();
    }
}

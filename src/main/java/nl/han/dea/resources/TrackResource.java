package nl.han.dea.resources;

import nl.han.dea.dto.UserDTO;
import nl.han.dea.persistence.dao.TracksDAO;
import nl.han.dea.persistence.dao.TracksDAOImpl;
import nl.han.dea.service.TokenValidationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {
    private TokenValidationService tokenValidationService;
    TracksDAO tracksDAO;
    UserDTO userDTO;

    public TrackResource() {
    }

    @Inject
    public TrackResource(TokenValidationService tokenValidationService, TracksDAO tracksDAO) {
        this.tokenValidationService = tokenValidationService;
        this.tracksDAO = tracksDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        return Response.ok().entity(tracksDAO.getAllTracks()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist) {
        userDTO = tokenValidationService.validateToken(token);

        return Response.ok().entity(tracksDAO.getAllTracksExceptInPlaylist(forPlaylist)).build();
    }
}

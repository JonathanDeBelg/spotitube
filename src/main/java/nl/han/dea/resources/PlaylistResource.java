package nl.han.dea.resources;

import nl.han.dea.dto.*;
import nl.han.dea.persistence.dao.PlaylistDAO;
import nl.han.dea.persistence.dao.PlaylistsDAO;
import nl.han.dea.persistence.dao.TracksDAO;
import nl.han.dea.service.TokenValidationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/playlists")
public class PlaylistResource {
    private TokenValidationService tokenValidationService;
    private PlaylistsDAO playListsDAO = new PlaylistsDAO();
    private PlaylistDAO playListDAO = new PlaylistDAO();
    private TracksDAO tracksDAO = new TracksDAO();

    private UserDTO userDTO;

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(TokenValidationService tokenValidationService) {
        this.tokenValidationService = tokenValidationService;
    }

    @QueryParam("token")
    public void tokenValidation(@QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);
        tokenValidationService.validateToken(token);
        System.out.println(userDTO);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists() {
        return Response.ok().entity(playListsDAO.getPlaylists()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id) {
        playListDAO.deletePlaylist(id);
        return Response.ok().entity(playListsDAO.getPlaylists()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist) {
        playListDAO.addPlaylist(playlist);
        return Response.ok().entity(playListsDAO.getPlaylists()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyPlaylist(@PathParam("id") int id, PlaylistDTO playlist) {
        playListDAO.modifyPlaylist(id, playlist);
        return Response.ok().entity(playListsDAO.getPlaylists()).build();
    }


    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksBelongingToPlaylist(@PathParam("id") int playlistId) {
        return Response.ok().entity(tracksDAO.getTracksBelongingToPlaylist(playlistId)).build();
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksBelongingToPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackID) {
        playListDAO.deleteTrackFromPlaylist(playlistId, trackID);
        return Response.ok().entity(tracksDAO.getTracksBelongingToPlaylist(playlistId)).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int playlistId, TrackDTO track) {
        playListDAO.addTrackToPlaylist(playlistId, track);
        return Response.ok().entity(tracksDAO.getTracksBelongingToPlaylist(playlistId)).build();
    }
}

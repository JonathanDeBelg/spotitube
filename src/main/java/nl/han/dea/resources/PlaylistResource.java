package nl.han.dea.resources;

import nl.han.dea.dto.*;
import nl.han.dea.persistence.dao.*;
import nl.han.dea.service.TokenValidationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    private TokenValidationService tokenValidationService;
    private PlaylistsDAO playListsDAO;
    private PlaylistDAO playListDAO;
    private TracksDAO tracksDAO;
    private TrackDAO trackDAO;
    private UserDTO userDTO;


    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(TokenValidationService tokenValidationService, PlaylistsDAO playListsDAO, PlaylistDAO playListDAO, TracksDAO tracksDAO, TrackDAO trackDAO) {
        this.tokenValidationService = tokenValidationService;
        this.playListsDAO = playListsDAO;
        this.playListDAO = playListDAO;
        this.tracksDAO = tracksDAO;
        this.trackDAO = trackDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        return Response.ok().entity(playListsDAO.getPlaylists(userDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        playListDAO.deletePlaylist(id);
        return Response.ok().entity(playListsDAO.getPlaylists(userDTO)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist, @QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        playListDAO.addPlaylist(userDTO, playlist);
        return Response.ok().entity(playListsDAO.getPlaylists(userDTO)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyPlaylist(@PathParam("id") int id, PlaylistDTO playlist, @QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        playListDAO.modifyPlaylist(id, playlist);
        return Response.ok().entity(playListsDAO.getPlaylists(userDTO)).build();
    }


    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksBelongingToPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        return Response.ok().entity(tracksDAO.getTracksBelongingToPlaylist(playlistId)).build();
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackID, @QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        trackDAO.deleteTrackFromPlaylist(playlistId, trackID);
        return Response.ok().entity(tracksDAO.getTracksBelongingToPlaylist(playlistId)).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int playlistId, TrackDTO track, @QueryParam("token") String token) {
        userDTO = tokenValidationService.validateToken(token);

        trackDAO.addTrackToPlaylist(playlistId, track);
        return Response.ok().entity(tracksDAO.getTracksBelongingToPlaylist(playlistId)).build();
    }
}
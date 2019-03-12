package nl.han.dea.dto;

import java.util.ArrayList;

public class PlaylistsDTO {
    private ArrayList<PlaylistDTO> playlists = new ArrayList<>();
    private int length;

    public PlaylistsDTO(int length) {
        this.length = length;
        playlists.add(new PlaylistDTO(1, "Elemenatory", true));
        playlists.add(new PlaylistDTO(2, "Musketiers 2", false));
    }

    public ArrayList<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

package nl.han.dea;

import java.util.ArrayList;

public class PlaylistDTO {
    private int ID;
    private String name;
    private boolean owner;
    private int length;
    private ArrayList<TrackDTO> tracks = new ArrayList<>();

    public PlaylistDTO(int ID, String name, boolean owner, int length) {
        this.ID = ID;
        this.name = name;
        this.owner = owner;
        this.length = length;
        tracks.add(new TrackDTO("Kaas"));
        tracks.add(new TrackDTO("Jaap"));
        tracks.add(new TrackDTO("Jan"));
        tracks.add(new TrackDTO("Krat pils"));
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}

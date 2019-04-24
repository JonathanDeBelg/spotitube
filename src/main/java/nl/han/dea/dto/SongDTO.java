package nl.han.dea.dto;

public class SongDTO {
    private String album;
    private int SongId;

    public SongDTO(String album, int songId) {
        this.album = album;
        SongId = songId;
    }

    public SongDTO() {
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }
}

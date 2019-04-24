package nl.han.dea.dto;

import java.util.Date;

public class VideoDTO {
    private int videoId;
    private String publicationDate;
    private String description;

    public VideoDTO(int videoId, String publicationDate, String description) {
        this.videoId = videoId;
        this.publicationDate = publicationDate;
        this.description = description;
    }

    public VideoDTO() {
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

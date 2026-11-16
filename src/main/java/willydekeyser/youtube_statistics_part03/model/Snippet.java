package willydekeyser.youtube_statistics_part03.model;

import jakarta.persistence.*;

@Entity
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Video video;
    private String title;
    @Lob
    private String description;
    private String publishedAt;
    private String channelTitle;

    public Snippet() {
    }

    public Snippet(String title, String description, String publishedAt, String channelTitle) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.channelTitle = channelTitle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", channelTitle='" + channelTitle + '\'' +
                '}';
    }

}

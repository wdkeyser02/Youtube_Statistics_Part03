package willydekeyser.youtube_statistics_part03.model;

import jakarta.persistence.*;

@Entity
public class ContentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String duration;
    @OneToOne
    @JoinColumn(name = "video_id", unique = true)
    private Video video;

    public ContentDetails() {
    }

    public ContentDetails(String duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ContentDetails{" +
                "id=" + id +
                ", duration='" + duration + '\'' +
                '}';
    }
}

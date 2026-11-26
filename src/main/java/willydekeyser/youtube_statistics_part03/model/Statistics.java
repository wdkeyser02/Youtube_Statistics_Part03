package willydekeyser.youtube_statistics_part03.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String viewCount;
    private String likeCount;
    private String commentCount;
    private LocalDateTime dateTime;
    @ManyToOne()
    private Video video;


    public Statistics() {
    }

    public Statistics(String viewCount, String likeCount, String commentCount) {
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.dateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {

        return commentCount;
    }

    public void setCommentCount(String commentCount) {

        this.commentCount = commentCount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", viewCount='" + viewCount + '\'' +
                ", likeCount='" + likeCount + '\'' +
                ", commentCount='" + commentCount + '\'' +
                '}';
    }

}

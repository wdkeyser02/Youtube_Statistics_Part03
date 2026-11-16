package willydekeyser.youtube_statistics_part03.model;

import jakarta.persistence.*;

@Entity
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Video video;
    private String viewCount;
    private String likeCount;
    private String commentCount;

    public Statistics() {
    }

    public Statistics(String viewCount, String likeCount, String commentCount) {
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
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

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", viewCount='" + viewCount + '\'' +
                ", likeCount='" + likeCount + '\'' +
                ", commentCount='" + commentCount + '\'' +
                '}';
    }
}

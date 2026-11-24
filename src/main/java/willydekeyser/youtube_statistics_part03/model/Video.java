package willydekeyser.youtube_statistics_part03.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kind;
    private String etag;
    private String videoid;
    @OneToOne(cascade = CascadeType.ALL)
    private Snippet snippet;
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<Statistics> statistics = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private ContentDetails contentDetails;

    public Video() {
    }

    public Video(String kind, String etag, String videoid, String duration) {
        this.kind = kind;
        this.etag = etag;
        this.videoid = videoid;
        this.snippet = new Snippet("Title", "Description", "PublishedAt", "ChannelTitle");
        //this.statistics.add(new Statistics("ViewCount", "LikeCount", "CommentCount"));
        this.contentDetails = new ContentDetails(duration);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getVideoid() {
        return videoid;
    }

    public void setVideoId(String videoid) {
        this.videoid = videoid;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    public ContentDetails getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id) && Objects.equals(kind, video.kind) && Objects.equals(etag, video.etag) && Objects.equals(videoid, video.videoid) && Objects.equals(snippet, video.snippet) && Objects.equals(statistics, video.statistics) && Objects.equals(contentDetails, video.contentDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kind, etag, videoid, snippet, statistics, contentDetails);
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                ", etag='" + etag + '\'' +
                ", videoid='" + videoid + '\'' +
                ", snippet=" + snippet +
                ", statistics=" + statistics +
                ", contentDetails=" + contentDetails +
                '}';
    }
}

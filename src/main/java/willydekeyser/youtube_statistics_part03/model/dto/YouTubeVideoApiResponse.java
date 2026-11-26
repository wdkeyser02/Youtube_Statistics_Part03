package willydekeyser.youtube_statistics_part03.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YouTubeVideoApiResponse(
        List<VideoItem> items) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record VideoItem (
            String kind,
            String etag,
            String id,
            Snippet snippet,
            Statistics statistics,
            ContentDetails contentDetails) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Snippet(
                String title,
                String description,
                String publishedAt,
                String channelTitle) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Statistics (
                String dateTime,
                String viewCount,
                String likeCount,
                String commentCount) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record ContentDetails (
                String duration) {}
    }
}

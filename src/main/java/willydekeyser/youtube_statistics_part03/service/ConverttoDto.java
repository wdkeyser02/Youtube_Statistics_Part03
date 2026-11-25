package willydekeyser.youtube_statistics_part03.service;

import org.springframework.stereotype.Component;
import willydekeyser.youtube_statistics_part03.model.ContentDetails;
import willydekeyser.youtube_statistics_part03.model.Snippet;
import willydekeyser.youtube_statistics_part03.model.Statistics;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse.VideoItem;

import java.util.List;

@Component
public class ConverttoDto {

    public YouTubeVideoApiResponse convert(List<Video> videos) {
        return new YouTubeVideoApiResponse(videos.stream()
                .map(this::convert)
                .toList());
    }

    public VideoItem convert(Video video) {
        return new VideoItem(
                video.getKind(),
                video.getEtag(),
                video.getVideoid(),
                convert(video.getSnippet()),
                convert(latest(video.getStatistics())),
                convert(video.getContentDetails()));
    }

    private VideoItem.Snippet convert(Snippet snippet) {
        if (snippet == null) return null;
        return new VideoItem.Snippet(
                snippet.getTitle(),
                snippet.getDescription(),
                snippet.getPublishedAt(),
                snippet.getChannelTitle());
    }

    private VideoItem.Statistics convert(Statistics statistics) {
        if (statistics == null) return null;
        return new VideoItem.Statistics(
                statistics.getViewCount(),
                statistics.getLikeCount(),
                statistics.getCommentCount());
    }

    private VideoItem.ContentDetails convert(ContentDetails contentDetails) {
        if (contentDetails == null) return null;
        return new VideoItem.ContentDetails(contentDetails.getDuration());
    }

    // Entity keeps a history of statistics; the DTO holds only one -> take the most recent.
    private Statistics latest(List<Statistics> statistics) {
        return (statistics == null || statistics.isEmpty()) ? null : statistics.getLast();
    }
}

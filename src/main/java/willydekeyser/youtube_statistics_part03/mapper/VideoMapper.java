package willydekeyser.youtube_statistics_part03.mapper;

import org.springframework.stereotype.Component;
import willydekeyser.youtube_statistics_part03.model.Statistics;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.Snippet;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;


@Component
public class VideoMapper {

    public Video toVideo(YouTubeVideoApiResponse.VideoItem videoItem) {

        Snippet snippet = new Snippet(videoItem.snippet().title(), videoItem.snippet().description(), videoItem.snippet().publishedAt(), videoItem.snippet().channelTitle());
        Statistics statistics = new Statistics(videoItem.statistics().viewCount(), videoItem.statistics().likeCount(), videoItem.statistics().commentCount());
        return new Video(videoItem.kind(), videoItem.etag(), videoItem.id(), snippet, statistics, videoItem.contentDetails().duration());
    }

}

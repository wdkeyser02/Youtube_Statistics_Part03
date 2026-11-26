package willydekeyser.youtube_statistics_part03.mapper;

import org.springframework.stereotype.Component;
import willydekeyser.youtube_statistics_part03.model.Statistics;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.Snippet;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;

import java.util.List;



@Component
public class VideoMapper {

    public Video Dto_to_Video(YouTubeVideoApiResponse.VideoItem videoItem) {
        Snippet snippet = new Snippet(videoItem.snippet().title(), videoItem.snippet().description(), videoItem.snippet().publishedAt(), videoItem.snippet().channelTitle());
        List<Statistics> statistics = List.of(new Statistics(videoItem.statistics().viewCount(), videoItem.statistics().likeCount(), videoItem.statistics().commentCount()));
        return new Video(videoItem.kind(), videoItem.etag(), videoItem.id(), snippet, statistics, videoItem.contentDetails().duration());
    }

}

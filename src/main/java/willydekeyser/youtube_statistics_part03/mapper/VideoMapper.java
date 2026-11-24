package willydekeyser.youtube_statistics_part03.mapper;

import org.springframework.stereotype.Component;
import willydekeyser.youtube_statistics_part03.model.Statistics;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;


@Component
public class VideoMapper {

    public Video toVideo(YouTubeVideoApiResponse.VideoItem videoItem) {

        Video video = new Video(videoItem.kind(), videoItem.etag(), videoItem.id(), videoItem.contentDetails().duration());
        video.getStatistics().add(new Statistics("ViewCount", "LikeCount", "CommentCount"));
        return video;
    }

}

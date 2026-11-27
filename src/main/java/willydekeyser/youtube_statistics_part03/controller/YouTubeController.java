package willydekeyser.youtube_statistics_part03.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import willydekeyser.youtube_statistics_part03.model.Statistics;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.repository.YouTubeStatisticsRepository;

import java.util.List;

@RestController
public class YouTubeController {

    private final YouTubeStatisticsRepository youTubeStatisticsRepository;

    public YouTubeController(YouTubeStatisticsRepository youTubeStatisticsRepository) {
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
    }

    @GetMapping("/api/video-info")
    public List<YouTubeVideoApiResponse.VideoItem.Statistics> getVideoInfo(@RequestParam String videoId) {
        Video video = youTubeStatisticsRepository.findByVideoid(videoId);
        if (video == null) {
            return null;
        }
        List<Statistics> statistics = video.getStatistics();
        return statistics.stream()
                .map(stat -> new YouTubeVideoApiResponse.VideoItem.Statistics(stat.getDateTime().toString(), stat.getViewCount(),
                        stat.getLikeCount(), stat.getCommentCount()))
                .toList();
    }
}

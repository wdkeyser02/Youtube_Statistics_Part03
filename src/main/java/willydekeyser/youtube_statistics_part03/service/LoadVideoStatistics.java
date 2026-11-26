package willydekeyser.youtube_statistics_part03.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import willydekeyser.youtube_statistics_part03.model.Statistics;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.repository.YouTubeStatisticsRepository;

import java.util.List;

@Service
public class LoadVideoStatistics {

    private final YouTubeService youTubeService;
    private final YouTubeStatisticsRepository youTubeStatisticsRepository;

    public LoadVideoStatistics(YouTubeService youTubeService, YouTubeStatisticsRepository youTubeStatisticsRepository) {
        this.youTubeService = youTubeService;
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void loadVideos() {
        List<Video> videos = youTubeStatisticsRepository.findAll();
        videos.forEach(video -> {
            YouTubeVideoApiResponse.VideoItem response = youTubeService.getVideoDetails(video.getVideoid());
            video.addStatistics(new Statistics(response.statistics().viewCount(), response.statistics().likeCount(), response.statistics().commentCount()));
            youTubeStatisticsRepository.save(video);
        });

    }

}

package willydekeyser.youtube_statistics_part03.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import willydekeyser.youtube_statistics_part03.mapper.VideoMapper;
import willydekeyser.youtube_statistics_part03.model.ContentDetails;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.repository.YouTubeStatisticsRepository;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey; //

    @Value("${youtube.api.video.url}")
    private String apiVideoUrl; // https://www.googleapis.com/youtube/v3/videos

    private final RestClient restClient = RestClient.create();
    private final YouTubeStatisticsRepository youTubeStatisticsRepository;
    private final VideoMapper videoMapper;

    public YouTubeService(YouTubeStatisticsRepository youTubeStatisticsRepository, VideoMapper videoMapper) {
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
        this.videoMapper = videoMapper;
    }

    public YouTubeVideoApiResponse.VideoItem getVideoDetails(String videoId) {
        String url = UriComponentsBuilder.fromUriString(apiVideoUrl)
                .queryParam("part", "snippet,statistics,contentDetails")
                .queryParam("id", videoId)
                .queryParam("key", apiKey)
                .toUriString();

        YouTubeVideoApiResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(YouTubeVideoApiResponse.class);

        if (response != null && response.items() != null && !response.items().isEmpty()) {

            ContentDetails contentDetails = new ContentDetails("Testen");
            Video test = new Video("","","", contentDetails);
            youTubeStatisticsRepository.save(test);
            return response.items().getFirst();
        }

        throw new RuntimeException("Video not found or no statistics available.");
    }

}

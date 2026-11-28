package willydekeyser.youtube_statistics_part03.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey; //

    @Value("${youtube.api.video.url}")
    private String apiVideoUrl; // https://www.googleapis.com/youtube/v3/videos

    private final RestClient restClient = RestClient.create();

    public YouTubeService() {

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
            return response.items().getFirst();
        }

        throw new RuntimeException("Video not found or no statistics available.");
    }

}

package willydekeyser.youtube_statistics_part03.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.service.YouTubeService;

@RestController
public class YouTubeController {

    @Value("${youtube.api.video.id}")
    private String apiVideoId; // vRQgnSHaGak

    private final YouTubeService youTubeService;

    public YouTubeController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    @GetMapping("/api/video-info") // http://localhost:8080/api/video-info?videoId=vRQgnSHaGak
    public YouTubeVideoApiResponse.VideoItem getVideoInfo(@RequestParam String videoId) {

        return youTubeService.getVideoDetails(videoId);
    }

    @GetMapping("/api/video-info-input") // vRQgnSHaGak
    public String getInputVideoInfo() {
        return "<input type=\"text\" id=\"videoId\" name=\"videoId\" value=" + apiVideoId + " size=\"50\">";
    }
}

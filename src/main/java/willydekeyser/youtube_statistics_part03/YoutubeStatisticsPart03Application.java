package willydekeyser.youtube_statistics_part03;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.service.LoadVideos;
import willydekeyser.youtube_statistics_part03.service.YouTubeService;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class YoutubeStatisticsPart03Application {

	private final LoadVideos loadVideos;
	private final YouTubeService youTubeService;

    public YoutubeStatisticsPart03Application(LoadVideos loadVideos, YouTubeService youTubeService) {
        this.loadVideos = loadVideos;
        this.youTubeService = youTubeService;
    }

    public static void main(String[] args) {
		SpringApplication.run(YoutubeStatisticsPart03Application.class, args);
	}

	@Bean
    CommandLineRunner runner(StandardServletMultipartResolver standardServletMultipartResolver) {
		return args -> {
			System.out.println("CommandLineRunner running in the UnsplashApplication class...");
			YouTubeVideoApiResponse.VideoItem response = youTubeService.getVideoDetails("mF0qh_9hwjM");

			loadVideos.loadVideos();


		};
	}

}

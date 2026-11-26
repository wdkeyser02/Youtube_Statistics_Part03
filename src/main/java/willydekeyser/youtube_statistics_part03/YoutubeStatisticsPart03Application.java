package willydekeyser.youtube_statistics_part03;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import willydekeyser.youtube_statistics_part03.mapper.VideoMapper;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.repository.YouTubeStatisticsRepository;
import willydekeyser.youtube_statistics_part03.service.YouTubeService;

@SpringBootApplication
@EnableScheduling
public class YoutubeStatisticsPart03Application {

	private final YouTubeService youTubeService;
	private final YouTubeStatisticsRepository youTubeStatisticsRepository;
	private final VideoMapper videoMapper;

    public YoutubeStatisticsPart03Application(YouTubeService youTubeService, YouTubeStatisticsRepository youTubeStatisticsRepository, VideoMapper videoMapper) {
        this.youTubeService = youTubeService;
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
        this.videoMapper = videoMapper;
    }

    public static void main(String[] args) {
		SpringApplication.run(YoutubeStatisticsPart03Application.class, args);
	}

	@Bean
    CommandLineRunner runner(StandardServletMultipartResolver standardServletMultipartResolver) {
		return args -> {
			System.out.println("CommandLineRunner running in the YoutubeStatisticsPart03Application...");
			YouTubeVideoApiResponse.VideoItem response;
			if (!youTubeStatisticsRepository.existsByVideoid("Wb8Tq_HD4E8")) {
				response = youTubeService.getVideoDetails("Wb8Tq_HD4E8");
				youTubeStatisticsRepository.save(videoMapper.Dto_to_Video(response));

			}
			if (!youTubeStatisticsRepository.existsByVideoid("izXcd-bU72k")) {
				response = youTubeService.getVideoDetails("izXcd-bU72k");
				youTubeStatisticsRepository.save(videoMapper.Dto_to_Video(response));
			}
		};
	}

}

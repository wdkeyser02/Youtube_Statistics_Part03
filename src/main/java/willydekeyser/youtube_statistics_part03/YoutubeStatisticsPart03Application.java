package willydekeyser.youtube_statistics_part03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import willydekeyser.youtube_statistics_part03.mapper.VideoMapper;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.repository.YouTubeStatisticsRepository;
import willydekeyser.youtube_statistics_part03.service.YouTubeService;

@SpringBootApplication
@EnableScheduling
public class YoutubeStatisticsPart03Application {

	@Value("${youtube.api.video1.id}")
	private String apiVideo1Id; // vRQgnSHaGak
	@Value("${youtube.api.video2.id}")
	private String apiVideo2Id; // vRQgnSHaGak
	@Value("${youtube.api.video3.id}")
	private String apiVideo3Id; // vRQgnSHaGak

	private final YouTubeService youTubeService;
	private final YouTubeStatisticsRepository youTubeStatisticsRepository;
	private final VideoMapper videoMapper;

    public YoutubeStatisticsPart03Application(YouTubeService youTubeService, YouTubeStatisticsRepository youTubeStatisticsRepository, VideoMapper videoMapper) {
        this.youTubeService = youTubeService;
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
        this.videoMapper = videoMapper;
    }

    static void main(String[] args) {
		SpringApplication.run(YoutubeStatisticsPart03Application.class, args);
	}

	@Bean
    CommandLineRunner runner() {
		return args -> {
			IO.println("CommandLineRunner running in the YoutubeStatisticsPart03Application...");
			YouTubeVideoApiResponse.VideoItem response;
			if (!youTubeStatisticsRepository.existsByVideoid(apiVideo1Id)) {
				response = youTubeService.getVideoDetails(apiVideo1Id);
				youTubeStatisticsRepository.save(videoMapper.Dto_to_Video(response));
			}
			if (!youTubeStatisticsRepository.existsByVideoid(apiVideo2Id)) {
				response = youTubeService.getVideoDetails(apiVideo2Id);
				youTubeStatisticsRepository.save(videoMapper.Dto_to_Video(response));
			}
			if (!youTubeStatisticsRepository.existsByVideoid(apiVideo3Id)) {
				response = youTubeService.getVideoDetails(apiVideo3Id);
				youTubeStatisticsRepository.save(videoMapper.Dto_to_Video(response));
			}
		};
	}

}

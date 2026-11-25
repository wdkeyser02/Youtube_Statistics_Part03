package willydekeyser.youtube_statistics_part03.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.model.dto.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics_part03.repository.YouTubeStatisticsRepository;

import java.util.List;

@Service
public class LoadVideos {

    private final YouTubeStatisticsRepository youTubeStatisticsRepository;
    private final ConverttoDto converttoDto;

    public LoadVideos(YouTubeStatisticsRepository youTubeStatisticsRepository, ConverttoDto converttoDto) {
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
        this.converttoDto = converttoDto;
    }

    //@Scheduled(fixedRate = 100_000)
    //@Transactional(readOnly = true)
    public YouTubeVideoApiResponse loadVideos() {
        List<Video> videos = youTubeStatisticsRepository.findAll();
        IO.println(videos);
        return converttoDto.convert(videos);
    }
}

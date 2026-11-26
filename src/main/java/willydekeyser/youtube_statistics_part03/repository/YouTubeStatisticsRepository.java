package willydekeyser.youtube_statistics_part03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import willydekeyser.youtube_statistics_part03.model.Video;

public interface YouTubeStatisticsRepository extends JpaRepository<Video, Long> {

    boolean existsByVideoid(String videoId);
    Video findByVideoid(String wb8TqHd4E8);
}

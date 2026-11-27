package willydekeyser.youtube_statistics_part03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import willydekeyser.youtube_statistics_part03.model.Statistics;
import willydekeyser.youtube_statistics_part03.model.Video;
import willydekeyser.youtube_statistics_part03.repository.YouTubeStatisticsRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
public class ViewController {

    private final YouTubeStatisticsRepository youTubeStatisticsRepository;

    public ViewController(YouTubeStatisticsRepository youTubeStatisticsRepository) {
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/chart-data")
    public String chartData(Model model, @RequestParam(defaultValue = "total", required = false) String chartOptions) {
        List<String> labels = new ArrayList<>();
        List<String> values = new ArrayList<>();
        Video video = youTubeStatisticsRepository.findByVideoid("Wb8Tq_HD4E8");
        if (video != null) {

            switch (chartOptions) {
                case "total":
                    labels = video.getStatistics().stream()
                            .map(stats -> stats.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                            .toList();
                    values = video.getStatistics().stream()
                            .map(Statistics::getViewCount)
                            .toList();
                    break;
                case "views":
                    labels = video.getStatistics().stream()
                            .skip(1)
                            .map(stats -> stats.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                            .toList();
                    values = IntStream.range(1, video.getStatistics().size())
                            .map(i -> Integer.parseInt(video.getStatistics().get(i).getViewCount())
                                    - Integer.parseInt(video.getStatistics().get(i - 1).getViewCount()))
                            .mapToObj(String::valueOf)
                            .toList();
                    break;
            }
        }
        model.addAttribute("labels", labels);
        model.addAttribute("values", values);
        return "chart-data";
    }
}

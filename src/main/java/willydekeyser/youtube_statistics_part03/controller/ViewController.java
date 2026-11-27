package willydekeyser.youtube_statistics_part03.controller;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${youtube.api.video1.id}")
    private String apiVideo1Id; // vRQgnSHaGak
    @Value("${youtube.api.video2.id}")
    private String apiVideo2Id; // vRQgnSHaGak
    @Value("${youtube.api.video3.id}")
    private String apiVideo3Id; // vRQgnSHaGak

    private final YouTubeStatisticsRepository youTubeStatisticsRepository;

    public ViewController(YouTubeStatisticsRepository youTubeStatisticsRepository) {
        this.youTubeStatisticsRepository = youTubeStatisticsRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("video1", apiVideo1Id);
        model.addAttribute("video2", apiVideo2Id);
        model.addAttribute("video3", apiVideo3Id);
        return "index";
    }

    @GetMapping("/chart-data")
    public String chartData(Model model, @RequestParam(defaultValue = "total", required = false) String chartOptions) {
        List<String> labels = new ArrayList<>();
        List<String> values1 = new ArrayList<>();
        List<String> values2 = new ArrayList<>();
        List<String> values3 = new ArrayList<>();
        Video video1 = youTubeStatisticsRepository.findByVideoid(apiVideo1Id);
        Video video2 = youTubeStatisticsRepository.findByVideoid(apiVideo2Id);
        Video video3 = youTubeStatisticsRepository.findByVideoid(apiVideo3Id);
        if (video1 != null && video2 != null && video3 != null) {
            switch (chartOptions) {
                case "total":
                    labels = video1.getStatistics().stream()
                            .map(stats -> stats.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                            .toList();
                    values1 = video1.getStatistics().stream()
                            .map(Statistics::getViewCount)
                            .toList();
                    values2 = video2.getStatistics().stream()
                            .map(Statistics::getViewCount)
                            .toList();
                    values3 = video3.getStatistics().stream()
                            .map(Statistics::getViewCount)
                            .toList();
                    break;
                case "views":
                    labels = video1.getStatistics().stream()
                            .skip(1)
                            .map(stats -> stats.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                            .toList();
                    values1 = IntStream.range(1, video1.getStatistics().size())
                            .map(i -> Integer.parseInt(video1.getStatistics().get(i).getViewCount())
                                    - Integer.parseInt(video1.getStatistics().get(i - 1).getViewCount()))
                            .mapToObj(String::valueOf)
                            .toList();
                    values2 = IntStream.range(1, video1.getStatistics().size())
                            .map(i -> Integer.parseInt(video2.getStatistics().get(i).getViewCount())
                                    - Integer.parseInt(video2.getStatistics().get(i - 1).getViewCount()))
                            .mapToObj(String::valueOf)
                            .toList();
                    values3 = IntStream.range(1, video3.getStatistics().size())
                            .map(i -> Integer.parseInt(video3.getStatistics().get(i).getViewCount())
                                    - Integer.parseInt(video3.getStatistics().get(i - 1).getViewCount()))
                            .mapToObj(String::valueOf)
                            .toList();
                    break;
            }
        }
        model.addAttribute("labels", labels);
        model.addAttribute("values1", values1);
        model.addAttribute("values2", values2);
        model.addAttribute("values3", values3);
        return "chart-data";
    }
}

package willydekeyser.youtube_statistics_part03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/chart-data")
    public String chartData(Model model) {

        Integer test1 = new Random().nextInt(100);
        Integer test2 = new Random().nextInt(100);
        Integer test3 = new Random().nextInt(100);
        Integer test4 = new Random().nextInt(100);
        model.addAttribute("labels", List.of("Jan", "Feb", "Mrt", "Apr", "Jan", "Feb", "Mrt", "Apr","Jan", "Feb", "Mrt", "Apr","Jan", "Feb", "Mrt", "Apr","Jan", "Feb", "Mrt", "Apr"));
        model.addAttribute("values", List.of(test1, test2, test3, test4,test1, test2, test3, test4,test1, test2, test3, test4,test1, test2, test3, test4,test1, test2, test3, test4,test1, test2, test3, test4));
        return "chart-data";
    }
}

package zeyaOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import zeyaOnline.model.Page;

@SpringBootApplication
@RestController
public class App {
    private List<Page> pages = new ArrayList<>();


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/pages")
    public Page create(@RequestBody Page page) {
        pages.add(page);
        return page;
    }

    @GetMapping("/pages")
    public List<Page> get(@RequestParam(defaultValue = "1") Integer limit) {
        return pages.stream().limit(limit).toList();
    }
}

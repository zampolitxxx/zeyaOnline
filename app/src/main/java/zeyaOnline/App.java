package zeyaOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/pages/{id}")
    public Optional<Page> show (@PathVariable String id) {
        return pages.stream()
                .filter(p -> p.getSlug().equals(id))
                .findFirst();
    }

    @DeleteMapping("/pages/{id}")
    public void delete(@PathVariable String id) {
        pages.removeIf(p -> p.getSlug().equals(id));
    }

    @PutMapping("/pages/{id}")
    public Page update(@PathVariable String id, @RequestBody Page data) {
        Optional<Page> maybePage = pages.stream()
                .filter(p -> p.getSlug().equals(id))
                .findFirst();
        if (maybePage.isPresent()) {
            Page result = maybePage.get();
            result.setSlug(data.getSlug());
            result.setName(data.getName());
            result.setBody(data.getBody());
        }
        return data;
    }
}

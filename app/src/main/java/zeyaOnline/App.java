package zeyaOnline;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import zeyaOnline.exception.ResourceNotFoundException;
import zeyaOnline.model.Page;
import zeyaOnline.repository.PageRepository;

@SpringBootApplication
@RestController
public class App {

    @Autowired
    private PageRepository pageRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @PostMapping("/pages")
    @ResponseStatus(HttpStatus.CREATED)
    public Page create(@Valid @RequestBody Page page) {
        pageRepository.save(page);
        return page;
    }

    @GetMapping("/pages")
    public List<Page> get(@RequestParam(defaultValue = "1") Integer limit) {
        return pageRepository.findAll();
    }

    @GetMapping("/pages/{id}")
    @ResponseStatus(HttpStatus.OK)
    Page show (@PathVariable Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id + "Not found"));
        return page;
    }

    @DeleteMapping("/pages/{id}")
    public void delete(@PathVariable Long id) {
        pageRepository.deleteById(id);
    }

//    @PutMapping("/pages/{id}")
//    public Page update(@PathVariable Long id, @RequestBody Page data) {
//        Optional<Page> maybePage = pages.stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst();
//        if (maybePage.isPresent()) {
//            Page result = maybePage.get();
//            result.setId(data.getId());
//            result.setName(data.getName());
//            result.setBody(data.getBody());
//        }
//        return data;
//    }
}

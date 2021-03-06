package hello.controller;

//import hello.domain.Film;
//import hello.repos.FilmRepo;
import hello.domain.Film;
import hello.repos.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private FilmRepo filmRepo;

    /*//метод для tymeleaf
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }*/
    /*//метод для mustache
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }*/
    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Film> films = filmRepo.findAll();
        model.put("films", films);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String rating, @RequestParam String year, Map<String, Object> model) {
        Film film = new Film(name, rating, year);
        filmRepo.save(film);
        Iterable<Film> films = filmRepo.findAll();
        model.put("films", films);
        return "main";
    }

}
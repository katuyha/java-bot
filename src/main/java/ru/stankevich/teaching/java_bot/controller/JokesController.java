package ru.stankevich.teaching.java_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.stankevich.teaching.java_bot.model.Jokes;
import ru.stankevich.teaching.java_bot.service.JokesService;
import ru.stankevich.teaching.java_bot.service.JokesServiceImpl;

import java.util.List;


@RequestMapping("/api/jokes")
@RestController
public class JokesController {

    private final JokesService jokesService;

    @Autowired
    public JokesController(JokesServiceImpl jokesService) {
        this.jokesService = jokesService;
    }

    @Secured({"ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<Jokes> addJokes(@RequestBody Jokes joke) {
        Jokes saved = jokesService.addJokes(joke);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @GetMapping
    public ResponseEntity<List<Jokes>> getAllJokes(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size", required = false, defaultValue = "50") Integer size
    ) {
        List<Jokes> jokes = jokesService.getAllJokes(title, page, size);
        return ResponseEntity.ok(jokes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jokes> getJokesById(@PathVariable("id") Long id) {
        Jokes joke = jokesService.getJokesById(id);
        return ResponseEntity.ok(joke);
    }

    @Secured({"ROLE_MODERATOR"})
    @PutMapping("/{id}")
    public ResponseEntity<Void> editJokes(@PathVariable("id") Long id, @RequestBody Jokes joke) {
        jokesService.editJokes(id, joke);
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_MODERATOR"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJokes(@PathVariable("id") Long id) {
        jokesService.deleteJokes(id);
        return ResponseEntity.ok().build();
    }
}

package ru.stankevich.teaching.java_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.stankevich.teaching.java_bot.exceptions.JokesNotFoundExceptions;
import ru.stankevich.teaching.java_bot.model.Jokes;
import ru.stankevich.teaching.java_bot.repository.JokesRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class JokesServiceImpl implements JokesService{

    private final JokesRepository jokesRepository;

    @Autowired
    public JokesServiceImpl(JokesRepository jokesRepository) {
        this.jokesRepository = jokesRepository;
    }

    public Jokes addJokes(Jokes joke) {
        return jokesRepository.save(joke);
    }

    public List<Jokes> getAllJokes(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());

        if (title != null && !title.isEmpty()) {
            return jokesRepository.findByTitleContainingIgnoreCase(title, pageable).getContent();
        } else {
            return jokesRepository.findAll(pageable).getContent();
        }
    }


    public Jokes getJokesById(Long id) {
        Optional<Jokes> jokes = jokesRepository.findById(id);
        if (jokes.isPresent()){
            return jokes.get();
        }
        else {
            throw new JokesNotFoundExceptions(id) ;
        }
    }

    public Void editJokes(Long id, Jokes joke) {
        if (!jokesRepository.existsById(id)) {
            throw new JokesNotFoundExceptions(id);
        }
        joke.setId(id);
        jokesRepository.save(joke);
        return null;
    }

    public Void deleteJokes(Long id) {
        if (!jokesRepository.existsById(id)) {
            throw new JokesNotFoundExceptions(id);
        }
        jokesRepository.deleteById(id);
        return null;
    }
}

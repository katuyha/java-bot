package ru.stankevich.teaching.java_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stankevich.teaching.java_bot.exceptions.JokesNotFoundExceptions;
import ru.stankevich.teaching.java_bot.model.Jokes;
import ru.stankevich.teaching.java_bot.repository.JokesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<Jokes> getAllJokes(String title) {
        if (title != null) {
            return StreamSupport.stream(jokesRepository.findAll().spliterator(), false)
                    .filter(joke -> title.equals(joke.getTitle()))
                    .collect(Collectors.toList());
        } else {
            return (List<Jokes>) jokesRepository.findAll();
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

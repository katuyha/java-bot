package ru.stankevich.teaching.java_bot.service;

import ru.stankevich.teaching.java_bot.model.Jokes;
import java.util.List;

public interface JokesService {


    public Jokes addJokes(Jokes joke);

    List<Jokes> getAllJokes(String title, int page, int size);

    public Jokes getJokesById(Long id);

    public Void editJokes(Long id, Jokes joke);

    public Void deleteJokes(Long id);
}


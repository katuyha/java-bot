package ru.stankevich.teaching.java_bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stankevich.teaching.java_bot.model.Jokes;

public interface JokesRepository extends CrudRepository<Jokes, Long> {
}

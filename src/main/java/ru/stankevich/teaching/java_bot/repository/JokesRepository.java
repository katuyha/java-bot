package ru.stankevich.teaching.java_bot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.stankevich.teaching.java_bot.model.Jokes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JokesRepository extends PagingAndSortingRepository<Jokes, Long>, CrudRepository<Jokes, Long> {

    Page<Jokes> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}

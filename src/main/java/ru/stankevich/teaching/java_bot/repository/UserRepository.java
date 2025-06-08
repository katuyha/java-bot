package ru.stankevich.teaching.java_bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stankevich.teaching.java_bot.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

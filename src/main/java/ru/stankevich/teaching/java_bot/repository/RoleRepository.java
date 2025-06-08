package ru.stankevich.teaching.java_bot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.stankevich.teaching.java_bot.model.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

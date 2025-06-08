package ru.stankevich.teaching.java_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.stankevich.teaching.java_bot.exceptions.RoleNotFoundExceptions;
import ru.stankevich.teaching.java_bot.exceptions.UserNotFoundExceptions;
import ru.stankevich.teaching.java_bot.model.Role;
import ru.stankevich.teaching.java_bot.model.User;
import ru.stankevich.teaching.java_bot.repository.RoleRepository;
import ru.stankevich.teaching.java_bot.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password) {
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundExceptions("ROLE_USER"));

        User user = new User()
                .setUsername(username)
                .setPassword(passwordEncoder.encode(password))
                .setRole(role);

        return userRepository.save(user);
    }

    @Override
    public String getRole(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundExceptions(username));
        return user.getRole().getName();
    }

    @Override
    public void updateRole(String username, String roleName) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundExceptions(username));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundExceptions(roleName));

        user.setRole(role);
        userRepository.save(user);
    }
}

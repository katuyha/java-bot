package ru.stankevich.teaching.java_bot.service;

import ru.stankevich.teaching.java_bot.model.User;

public interface UserService {

        User register(String username, String password);
        String getRole(String username);
        void updateRole(String username, String roleName);
}
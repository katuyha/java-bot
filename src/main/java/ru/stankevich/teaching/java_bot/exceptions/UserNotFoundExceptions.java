package ru.stankevich.teaching.java_bot.exceptions;


import lombok.Getter;

@Getter
public class UserNotFoundExceptions extends RuntimeException {
    private final String username;

    public UserNotFoundExceptions(String username) {
        super("User not found: " + username);
        this.username = username;
    }
}
package ru.stankevich.teaching.java_bot.exceptions;

import lombok.Getter;

@Getter
public class RoleNotFoundExceptions extends RuntimeException {
    private final String roleName;

    public RoleNotFoundExceptions(String roleName) {
        super("Role not found: " + roleName);
        this.roleName = roleName;
    }
}
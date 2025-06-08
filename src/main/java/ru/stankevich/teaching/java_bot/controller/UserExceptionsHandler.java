package ru.stankevich.teaching.java_bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.stankevich.teaching.java_bot.exceptions.RoleNotFoundExceptions;
import ru.stankevich.teaching.java_bot.exceptions.UserNotFoundExceptions;


@ControllerAdvice
public class UserExceptionsHandler {

    @ExceptionHandler(UserNotFoundExceptions.class)
    public ResponseEntity<Void> handleUserNotFound(UserNotFoundExceptions exception) {

        System.out.println("User not found");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RoleNotFoundExceptions.class)
    public ResponseEntity<Void> handleRoleNotFound(RoleNotFoundExceptions exception) {

        System.out.println("Role not found");
        return ResponseEntity.notFound().build();
    }
}
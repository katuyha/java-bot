package ru.stankevich.teaching.java_bot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.stankevich.teaching.java_bot.exceptions.ExceptionRespone;
import ru.stankevich.teaching.java_bot.exceptions.JokesNotFoundExceptions;

@ControllerAdvice
public class JokesExceptionsHandler {

    @ExceptionHandler(JokesNotFoundExceptions.class)
    public ResponseEntity<ExceptionRespone> handleJokesNotFound (JokesNotFoundExceptions exception) {

        System.out.println("Joke not found with ID: " + exception.getId());
        return ResponseEntity.notFound().build();
    }
}

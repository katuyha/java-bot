package ru.stankevich.teaching.java_bot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "challengesJokes")
public class ChallengesJokes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teach_id_seq")
    @SequenceGenerator(sequenceName = "teach_id_seq", name = "teach_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "dateTime")
    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "jokesId")
    private Jokes jokes;

    // Геттеры
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Jokes getJokes() {
        return jokes;
    }

    // Сеттеры
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setJokes(Jokes jokes) {
        this.jokes = jokes;
    }
}

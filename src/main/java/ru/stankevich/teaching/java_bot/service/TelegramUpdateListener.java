package ru.stankevich.teaching.java_bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.stankevich.teaching.java_bot.model.ChallengesJokes;
import ru.stankevich.teaching.java_bot.model.Jokes;
import ru.stankevich.teaching.java_bot.repository.ChallengesJokesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TelegramUpdateListener implements UpdatesListener {

    private final JokesService jokesService;
    private final TelegramBot telegramBot;
    private final ChallengesJokesRepository challengesJokesRepository;

    public TelegramUpdateListener(JokesService jokesService, TelegramBot telegramBot, ChallengesJokesRepository challengesJokesRepository) {
        this.jokesService = jokesService;
        this.telegramBot = telegramBot;
        this.challengesJokesRepository = challengesJokesRepository;
    }

    @Override
    public int process(List<Update> list) {
        for (Update update : list) {
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                Long chatId = update.message().chat().id();

                if (messageText.equals("/start")) {
                    telegramBot.execute(new SendMessage(chatId,
                            "Доступные команды:\n/jokes - случайная шутка"));
                }

                if (update.message().text().equals("/jokes")) {
                    List<Jokes> jokes = jokesService.getAllJokes(null, 0, 50);

                    if (!jokes.isEmpty()) {
                        Jokes joke = jokes.get((int) (Math.random() * jokes.size()));
                        String text = '"'+ joke.getTitle() + '"' +'.'+ "\n" + joke.getContent();
                        telegramBot.execute(new SendMessage(update.message().chat().id(), text));

                        // Записываем вызов шутки в БД
                        ChallengesJokes call = new ChallengesJokes();
                        call.setJokes(joke);
                        call.setUserId(chatId);
                        call.setDateTime(LocalDateTime.now());
                        challengesJokesRepository.save(call);
                    }

                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        telegramBot.setUpdatesListener(this);
    }
}

package com.example.ScheduleBot;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ScheduleBot extends TelegramLongPollingBot {

    private final String botName;
    private final String token;
    private final MessageSender messageSender;

    public ScheduleBot(
            @Value("${botName}") String botName,
            @Value("${token}") String token,
            MessageSender messageSender) {
        this.botName = botName;
        this.token = token;
        this.messageSender = messageSender;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            String responseText;

            if (message.hasText()) {
                String messageText = message.getText();
                if ("/start".equals(messageText)) {
                    responseText = "Добро пожаловать!";
                } else if (messageText.startsWith("Кнопка ")) {
                    responseText = "Вы нажали кнопку"; // обработка нажатия кнопки
                } else {
                    responseText = "Вы написали: *" + messageText + "*";
                }

            } else {
                responseText = "Я понимаю только текст";
            }

            messageSender.sendNotification(this,chatId, responseText);
        }
    }
}

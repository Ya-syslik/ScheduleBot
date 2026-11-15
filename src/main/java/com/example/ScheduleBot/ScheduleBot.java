package com.example.ScheduleBot;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class ScheduleBot extends TelegramLongPollingBot {
    @Value("${botName}")
    private String botName;

    @Value("${token}")
    private String token;

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
                } else {
                    responseText = "Вы написали: *" + messageText + "*";
                }
            } else {
                responseText = "Я понимаю только текст";
            }

            sendNotification(chatId, responseText);
        }
    }
    private void sendNotification(Long chatId, String responseText) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(chatId.toString());
        responseMessage.setText(responseText);
        responseMessage.enableMarkdown(true);
        try {
            execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new IllegalStateException("Не удалось отправить сообщение", e);
        }
    }


}

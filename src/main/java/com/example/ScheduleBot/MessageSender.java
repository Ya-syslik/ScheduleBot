package com.example.ScheduleBot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MessageSender {

    private final KeyboardFactory keyboardFactory;

    MessageSender(KeyboardFactory keyboardFactory){
        this.keyboardFactory=keyboardFactory;
    }

    public void sendNotification(TelegramLongPollingBot bot, Long chatId, String responseText) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(chatId.toString());
        responseMessage.setText(responseText);
        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(keyboardFactory.getReplyMarkup());
        try {
            bot.execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new IllegalStateException("Не удалось отправить сообщение", e);
        }
    }
}

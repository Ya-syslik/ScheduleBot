package com.example.ScheduleBot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardFactory {

    public ReplyKeyboardMarkup getReplyMarkup() {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Кнопка 1");
        firstRow.add("Кнопка 2");

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("Кнопка 3");
        secondRow.add("Кнопка 4");

        keyboard.add(firstRow);
        keyboard.add(secondRow);
        markup.setKeyboard(keyboard);
        markup.setResizeKeyboard(true);
        return markup;
    }
}

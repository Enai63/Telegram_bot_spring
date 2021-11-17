package ru.enai.bot.messages;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.enai.service.GetWeather;
import ru.enai.service.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class NameMessage implements MessageCreator {

    @Override
    public SendMessage createMessage(Message message) {
        GetWeather getWeather = new WeatherService();
        SendMessage sendMessage= new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyMarkup(getKeyboardStart());
        sendMessage.setText(getWeather.getWeather(message.getText()).toString());
        return sendMessage;
    }

        private ReplyKeyboardMarkup getKeyboardStart() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow oneKeyboardRow = new KeyboardRow();
        KeyboardButton buttonOne = new KeyboardButton();
        buttonOne.setText("/start");
        buttonOne.getText();
        oneKeyboardRow.add(buttonOne);
        keyboard.add(oneKeyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}

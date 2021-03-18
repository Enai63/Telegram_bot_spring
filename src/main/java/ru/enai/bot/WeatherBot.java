package ru.enai.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.enai.model.Weather;
import ru.enai.service.ServiceWeather;

import java.util.*;

@Component
public class WeatherBot extends TelegramLongPollingBot {
    @Value(value = "${spring.bot.name}")
    private String botName;
    @Value(value = "${spring.bot.token}")
    private String botToken;

    private List<Long> listChatID = new ArrayList<>();

    private final ServiceWeather serviceWeather;

    public WeatherBot(ServiceWeather serviceWeather) {
        this.serviceWeather = serviceWeather;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if ("/start".equals(message.getText())) {
                listChatID.add(message.getChatId());
                SendMessage sendMessage = new SendMessage();
                sendMessage.enableMarkdown(true);
                sendMessage.setReplyMarkup(getKeyboard());
                sendMessage.setReplyToMessageId(message.getMessageId());
                sendMessage.setChatId(message.getChatId().toString());
                sendMessage.setText("Выберите из: Локации или Название населенного пунткта");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                if (listChatID.contains(message.getChatId())) {
                    if (message.getLocation() != null) {
                        SendMessage sendMessage = new SendMessage();
                        Weather weather = serviceWeather.getWeatherLocation(message.getLocation());
                        sendMessage.setChatId(message.getChatId().toString());
                        sendMessage.setText(weather.toString());
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        listChatID.remove(message.getChatId());
                    } else if ("Насселенный пункт".equals(message.getText())) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setReplyToMessageId(message.getMessageId());
                        sendMessage.setChatId(message.getChatId().toString());
                        sendMessage.setText("В ответ на это сообщение отправте название населенного пункта");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                    } else {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId().toString());
                        Weather weather = serviceWeather.getWeatherCity(message.getText());
                        sendMessage.setText(weather.toString());
                        listChatID.remove(message.getChatId());
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        System.out.println(weather.toString());
                    }
                }
            }
        }
    }


    private ReplyKeyboardMarkup getKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow oneKeyboardRow = new KeyboardRow();

        KeyboardButton buttonOne = new KeyboardButton();
        buttonOne.setText("Ваша геолокация");
        buttonOne.setRequestLocation(true);
        buttonOne.getRequestLocation();

        KeyboardButton buttonTwo = new KeyboardButton();
        buttonTwo.setText("Насселенный пункт");
        buttonTwo.getText();

        oneKeyboardRow.add(buttonOne);
        oneKeyboardRow.add(buttonTwo);

        keyboard.add(oneKeyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}

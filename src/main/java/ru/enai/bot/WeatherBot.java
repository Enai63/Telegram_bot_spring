package ru.enai.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.enai.bot.factory.MessageFactory;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherBot extends TelegramLongPollingBot {
    @Value(value = "${spring.bot.name}")
    private String botName;
    @Value(value = "${spring.bot.token}")
    private String botToken;

    private final MessageFactory messageFactory;


    public WeatherBot(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }


    @Override
    public String getBotUsername() {
        return this.botName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {      //TODO Refactorings this method
        if (update.hasMessage()) {
            Message message = update.getMessage();
           // SendMessage sendMessage = messageFactory.getNewMessage(message);
            SendMessage sendMessage = messageFactory.getNewMessage(message);
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println("error");
            }

//            if ("/start".equals(message.getText())) {
//                listChatID.add(message.getChatId());
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.enableMarkdown(true);
//                sendMessage.setReplyMarkup(getKeyboardService());
//                sendMessage.setReplyToMessageId(message.getMessageId());
//                sendMessage.setChatId(message.getChatId().toString());
//                sendMessage.setText("Выберите из: Локации или Название населенного пунткта");
//                try {
//                    execute(sendMessage);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                if (listChatID.contains(message.getChatId())) {
//                    if (message.getLocation() != null) {
//                        SendMessage sendMessage = new SendMessage();
//                    //    Weather weather = serviceWeather.getWeatherLocation(message.getLocation());
//                        sendMessage.setChatId(message.getChatId().toString());
//                  //      sendMessage.setText(weather.toString());
//                        sendMessage.setReplyMarkup(getKeyboardStart());
//                        sendMessage.setReplyToMessageId(message.getMessageId());
//                        try {
//                            execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                        listChatID.remove(message.getChatId());
//                    } else if ("Насселенный пункт".equals(message.getText())) {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setReplyToMessageId(message.getMessageId());
//                        sendMessage.setChatId(message.getChatId().toString());
//                        sendMessage.setText("В ответ на это сообщение отправте название населенного пункта");
//                        try {
//                            execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//
//                    } else {
//                        SendMessage sendMessage = new SendMessage();
//                        sendMessage.setChatId(message.getChatId().toString());
//                   //     Weather weather = serviceWeather.getWeatherCity(message.getText());
//                   //     sendMessage.setText(weather.toString());
//                        sendMessage.setReplyMarkup(getKeyboardStart());
//                        sendMessage.setReplyToMessageId(message.getMessageId());
//                        listChatID.remove(message.getChatId());
//                        try {
//                            execute(sendMessage);
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
        }
    }



    }

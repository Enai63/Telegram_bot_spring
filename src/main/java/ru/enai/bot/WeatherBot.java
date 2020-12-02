package ru.enai.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.enai.Service.FinderService;


@Component
public class WeatherBot extends TelegramLongPollingBot {
    @Value(value = "${spring.bot.name")
    private String botName;
    @Value(value = "${spring.bot.token}")
    private String botToken;

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

        if(update.hasMessage()) {
            Message message = update.getMessage();

            if(message.hasText()){
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("you said: " + message.getText());

                try {
                    execute(sendMessageRequest);
                } catch (TelegramApiException e) {

                }
            }
        }
    }

}

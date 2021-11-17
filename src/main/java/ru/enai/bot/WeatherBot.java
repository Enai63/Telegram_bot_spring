package ru.enai.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.enai.bot.messages.MessageFactory;

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
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage sendMessage = messageFactory.getNewMessage(message);
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println("error");
            }
        }
    }
}

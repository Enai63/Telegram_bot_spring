package ru.enai.bot;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.enai.Service.FinderService;
import ru.enai.model.Weather;


@Component
public class WeatherBot extends TelegramLongPollingBot {
    private final FinderService service;
    private final Weather weather;
    @Value(value = "${spring.bot.name")
    private String botName;
    @Value(value = "${spring.bot.token}")
    private String botToken;

    public WeatherBot(FinderService service, Weather weather) {
        this.service = service;
        this.weather = weather;
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

        if(update.hasMessage()) {
            Message message = update.getMessage();
            if(message.hasText() || message.getLocation() != null){
                SendMessage sendMessage = null;
                if(message.getLocation() != null) {
                    sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId().toString());
                    sendMessage.setText("Пришлите вашу геолакацию, и я пришлю вам погоду в вашем месте нахождения");
                        System.out.println("Location true");
                        Location location = message.getLocation();
                        service.getParams(location.getLatitude(), location.getLongitude());
                        System.out.println(weather.getTemperature());
                    }
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                }
            }
        }
    }
}

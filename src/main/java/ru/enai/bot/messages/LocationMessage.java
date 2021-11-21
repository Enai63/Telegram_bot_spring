package ru.enai.bot.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.enai.service.WeatherService;


public class LocationMessage implements MessageCreator {


    @Override
    public SendMessage createMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(new WeatherService().getWeather(message.getLocation()).toString());
        return sendMessage;
    }
}

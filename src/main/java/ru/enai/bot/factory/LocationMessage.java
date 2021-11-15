package ru.enai.bot.factory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

//@Qualifier("locationMessage")
//@Component
public class LocationMessage implements MessageCreator {


    @Override
    public SendMessage getNewMessage(Message message) {
        return null;
    }
}

package ru.enai.bot.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public interface MessageCreator {
    SendMessage getNewMessage(Message message);
}

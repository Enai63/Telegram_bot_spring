package ru.enai.bot.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Optional;

import static ru.enai.bot.Command.LOCATION;
import static ru.enai.bot.Command.START;

@Component
public class MessageFactory {

    HashMap<String, MessageCreator> messageCreatorHashMap;

    public MessageFactory() {
        messageCreatorHashMap = new HashMap<>();
        messageCreatorHashMap.put(START, new WelcomeMessage());
        messageCreatorHashMap.put(LOCATION, new LocationMessage());
    }

    public SendMessage getNewMessage(Message message) {
        String command = message.getText();
        return Optional.ofNullable(messageCreatorHashMap.get(command).getNewMessage(message))
                .orElseThrow(() -> new IllegalStateException("No such command"));
    }




}

package ru.enai.bot.messages;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Optional;

import static ru.enai.bot.Command.*;

@Component
public class MessageFactory {

    HashMap<String, MessageCreator> messageCreator;

    public MessageFactory() {
        messageCreator = new HashMap<>();
        messageCreator.put(START, new WelcomeMessage());
        messageCreator.put(LOCATION, new LocationMessage());
        messageCreator.put(NAME, new NameMessage());
    }

    public SendMessage getNewMessage(Message message) {
        String command = wrapperCommand(message);
        System.out.println(command);
        return Optional.ofNullable(messageCreator.get(command).createMessage(message))
                .orElseThrow(() -> new IllegalStateException("No such command"));
    }

    private String wrapperCommand(Message message) {
        String command = "";
        if (message.hasLocation()) {
            command = LOCATION;
        } else if (message.getText().matches(NAME_CITY)) {
            command = NAME;
        } else if (message.getText().equalsIgnoreCase(START)) {
            command = START;
        }
        return command;
    }

}

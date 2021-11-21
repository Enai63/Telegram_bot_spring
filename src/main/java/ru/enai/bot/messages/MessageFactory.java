package ru.enai.bot.messages;



import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.enai.bot.Command.*;

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
        return Optional.ofNullable(messageCreator.get(command).createMessage(message))
                .orElseThrow(() -> new IllegalStateException("No such command"));
    }

    private String wrapperCommand(Message message) {
        String command = "";
        if (message.getText() == null && message.hasLocation()) {
            command = LOCATION;
        } else if (message.getText().equalsIgnoreCase(START)) {
            command = START;
        } else {
            Pattern pattern = Pattern.compile(NAME_CITY);
            Matcher matcher = pattern.matcher(message.getText());
            if (matcher.matches()) {
                command = NAME;
            }
        }
        return command;
    }

}

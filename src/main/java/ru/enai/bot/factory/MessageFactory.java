package ru.enai.bot.factory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static ru.enai.bot.Command.LOCATION;
import static ru.enai.bot.Command.START;

@Component
public class MessageFactory {

    private final MessageCreator welcomeMessage;
    private final MessageCreator locationMessage;



    public MessageFactory(@Qualifier("welcomeMessage") MessageCreator welcomeMessage,
                          @Qualifier("locationMessage") MessageCreator locationMessage) {
        this.welcomeMessage = welcomeMessage;
        this.locationMessage = locationMessage;
    }

    public SendMessage getNewMessage(Message message) {
        String command = message.getText();

        SendMessage sendMessage;
        switch (command) {
            case START:
                sendMessage = welcomeMessage.getNewMessage(message);
                break;
            case LOCATION:
                sendMessage = locationMessage.getNewMessage(message);
                break;
            default:
                sendMessage = null;
        }
        return sendMessage;
    }


}

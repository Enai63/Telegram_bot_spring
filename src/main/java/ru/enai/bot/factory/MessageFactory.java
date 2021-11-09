package ru.enai.bot.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.enai.bot.Constant;
import static ru.enai.bot.Command.START;

@Component
public class MessageFactory {
    private SendMessage sendMessage;

    public SendMessage getNewMessage(Message message) {
        String command = message.getText();
        switch (command) {
            case START:
                sendMessage = new WelcomeMessage().getMessage(message.getChatId(), Constant.WELCOME_MESSAGE);
                break;
            default:
                sendMessage = new WelcomeMessage().getMessage(message.getChatId(), "Command not found");
        }
        return sendMessage;
    }


}

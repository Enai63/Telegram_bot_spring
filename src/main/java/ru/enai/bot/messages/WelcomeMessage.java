package ru.enai.bot.messages;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.enai.bot.Constant;

import java.util.ArrayList;
import java.util.List;

@Component
public class WelcomeMessage implements MessageCreator {

    @Override
    public SendMessage createMessage(Message message) {
        SendMessage sendMessage= new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(Constant.WELCOME_MESSAGE);
        sendMessage.setReplyMarkup(getKeyboardService());
        return sendMessage;
    }


    private ReplyKeyboardMarkup getKeyboardService() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow oneKeyboardRow = new KeyboardRow();

        KeyboardButton buttonOne = new KeyboardButton();
        buttonOne.setRequestLocation(true);
        buttonOne.setText("/location");
        buttonOne.getRequestPoll();




        KeyboardButton buttonTwo = new KeyboardButton();
        buttonTwo.setText("/name");
        buttonTwo.getText();
        buttonTwo.getRequestPoll();

        oneKeyboardRow.add(buttonOne);
        oneKeyboardRow.add(buttonTwo);

        keyboard.add(oneKeyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

}

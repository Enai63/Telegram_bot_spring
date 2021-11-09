package ru.enai.bot.factory;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.enai.bot.Constant;

import java.util.ArrayList;
import java.util.List;

public class WelcomeMessage implements Message {

    @Override
    public SendMessage getMessage(Long chatId, String text) {
        SendMessage sendMessage= new SendMessage(chatId.toString(), Constant.WELCOME_MESSAGE);
        sendMessage.setReplyMarkup(getKeyboardService());
        return sendMessage;
    }


//    private ReplyKeyboardMarkup getKeyboardStart() {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);
//        List<KeyboardRow> keyboard = new ArrayList<>();
//        KeyboardRow oneKeyboardRow = new KeyboardRow();
//        KeyboardButton buttonOne = new KeyboardButton();
//        buttonOne.setText("start");
//        buttonOne.getText();
//        oneKeyboardRow.add(buttonOne);
//        keyboard.add(oneKeyboardRow);
//        replyKeyboardMarkup.setKeyboard(keyboard);
//        return replyKeyboardMarkup;
//    }

    private ReplyKeyboardMarkup getKeyboardService() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow oneKeyboardRow = new KeyboardRow();

        KeyboardButton buttonOne = new KeyboardButton();
        buttonOne.setText("Ваша геолокация");
        buttonOne.setRequestLocation(true);
        buttonOne.getRequestLocation();

        KeyboardButton buttonTwo = new KeyboardButton();
        buttonTwo.getRequestPoll();
        buttonTwo.setText("Населенный пункт");
        buttonTwo.getText();

        oneKeyboardRow.add(buttonOne);
        oneKeyboardRow.add(buttonTwo);

        keyboard.add(oneKeyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}

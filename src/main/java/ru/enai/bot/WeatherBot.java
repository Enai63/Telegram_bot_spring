package ru.enai.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.enai.Service.WeatherService;

import java.io.IOException;

@Component
public class WeatherBot extends TelegramLongPollingBot {
    private final WeatherService weatherService;
    @Value(value = "${spring.bot.name")
    private String botName;
    @Value(value = "${spring.bot.token}")
    private String botToken;

    public WeatherBot(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //check if the update has a message
        if(update.hasMessage()){
            Message message = update.getMessage();
            if (message.getText().equals("start")) {
                    weatherService.getURL(53.188589, 50.200346);
            }
            //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
            if(message.hasText()){

                //create a object that contains the information to send back the message
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
                sendMessageRequest.setText("you said: " + message.getText());
                try {
                    execute(sendMessageRequest); //at the end, so some magic and send the message ;)
                } catch (TelegramApiException e) {
                    //do some error handling
                }//end catch()
            }//end if()
        }//end  if()

    }
}

package ru.enai.bot.factory;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Message {
    SendMessage getMessage(Long chatId, String text);
}

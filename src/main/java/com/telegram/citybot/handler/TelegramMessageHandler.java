package com.telegram.citybot.handler;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface TelegramMessageHandler {

    void handle(Update update) throws TelegramApiException;

}

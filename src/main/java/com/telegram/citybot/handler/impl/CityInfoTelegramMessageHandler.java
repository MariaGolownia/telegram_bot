package com.telegram.citybot.handler.impl;

import com.telegram.citybot.entity.CityInfo;
import com.telegram.citybot.handler.TelegramMessageHandler;
import com.telegram.citybot.repo.CityInfoRepo;
import com.telegram.citybot.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class CityInfoTelegramMessageHandler implements TelegramMessageHandler {

    public static final String MESSAGE_CITY_NOT_FOUND = "Параметры города не найдены в базе данных!" +
            " Пожалуйста, проверьте вводимые данные и(или) обратитесь к администратору приложения.";
    public static final String MESSAGE_CITY_INFO_NOT_FOUND = "В данном городе, как и в любом новом месте, безусловно," +
            "много туристических объектов, достойных Вашего внимания. Более подробную информацию можете узнать по телефону:" +
            "+37529 XXX-XX-XX";

    private TelegramBotService telegramBotService;

    private CityInfoRepo cityInfoRepo;

    @Autowired
    public CityInfoTelegramMessageHandler(TelegramBotService telegramBotService, CityInfoRepo cityInfoRepo) {
        this.telegramBotService = telegramBotService;
        this.cityInfoRepo = cityInfoRepo;
    }

    @Override
    public void handle(Update update) throws TelegramApiException {
        if (update.getMessage().getText().startsWith(TelegramBotService.INFO_ABOUT_BOT_BUTTON)
                || update.getMessage().getText().startsWith(TelegramBotService.RANDOM_CITY_BUTTON)
                || update.getMessage().getText().startsWith(TelegramBotService.START_COMMAND)) {
            return;
        }
        //проверяем есть ли сообщение и текстовое ли оно
        if (update.hasMessage() && update.getMessage().hasText()) {
            //Справочно:
            //Извлекаем объект входящего сообщения
            //Message inMessage = update.getMessage();
            //Создаем исходящее сообщение
            //SendMessage outMessage = new SendMessage();
            //Указываем в какой чат будем отправлять сообщение
            //(в тот же чат, откуда пришло входящее сообщение)
            //outMessage.setChatId(inMessage.getChatId());
            //Указываем текст сообщения
            //outMessage.setText(inMessage.getText());
            //Отправляем сообщение
            //execute(outMessage);
            Long chatId = update.getMessage().getChatId();
            String nameCityFromUser = update.getMessage().getText().toLowerCase().trim();
            String nameCityFromUserFormat = nameCityFromUser.substring(0, 1).toUpperCase();
            nameCityFromUserFormat = nameCityFromUserFormat + nameCityFromUser.substring(1);
            List<CityInfo> cityInfoList = cityInfoRepo.findByCity(nameCityFromUserFormat);

            if (cityInfoList == null || cityInfoList.size() == 0) {
                telegramBotService.sendTextMessage(chatId, MESSAGE_CITY_NOT_FOUND);
            } else {
                String messageSuccess = "";
                for (CityInfo cityInfo : cityInfoList) {
                    if (cityInfo.getInfo() == null || cityInfo.getInfo().trim().equals("")) {
                        cityInfo.setInfo(MESSAGE_CITY_INFO_NOT_FOUND);
                    }
                    messageSuccess += cityInfo.toString();
                }
                telegramBotService.sendTextMessage(chatId, messageSuccess);
            }
        } else {
            throw new TelegramApiException("Communication error with the bot!");
        }
    }

}

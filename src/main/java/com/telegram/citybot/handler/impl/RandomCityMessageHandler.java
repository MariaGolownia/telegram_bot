package com.telegram.citybot.handler.impl;
import com.telegram.citybot.entity.CityInfo;
import com.telegram.citybot.handler.TelegramMessageHandler;
import com.telegram.citybot.repo.CityInfoRepo;
import com.telegram.citybot.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import java.util.List;


@Component
public class RandomCityMessageHandler implements TelegramMessageHandler {

    private final static String INFO_BEFORE_RANDOM_CITY = "\nДля Вас сгенерирован рандомный город! " +
            "Мы желаем Вам побывать в нем! Увлекательного путешествия в городе: \n\n";
    private TelegramBotService telegramBotService;
    private CityInfoRepo cityInfoRepo;

    @Autowired
    public RandomCityMessageHandler(TelegramBotService telegramBotService, CityInfoRepo cityInfoRepo) {
        this.telegramBotService = telegramBotService;
        this.cityInfoRepo = cityInfoRepo;
    }

    @Override
    public void handle(Update update) {
        if (!update.getMessage().getText().startsWith(TelegramBotService.RANDOM_CITY_BUTTON)) {
            return;
        }
        Long chatId = update.getMessage().getChatId();
        User user = update.getMessage().getFrom();
        List<CityInfo> cityInfoList = cityInfoRepo.findAll();
        Integer indexRandomList = rnd (0, cityInfoList.size()-1);
        CityInfo cityInfoRandom = cityInfoList.get(indexRandomList);
        String randomCityInform = cityInfoRandom.toString();
         telegramBotService.sendTextMessage(chatId, INFO_BEFORE_RANDOM_CITY + randomCityInform);
    }

    /**
     * Метод получения псевдослучайного целого числа от min до max (включая max);
     */
    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}

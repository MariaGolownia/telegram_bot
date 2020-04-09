package com.telegram.citybot.handler.impl;
import com.telegram.citybot.handler.TelegramMessageHandler;
import com.telegram.citybot.service.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InfoAboutBotMessageHandler implements TelegramMessageHandler {

        private final static String INFO_ABOUT_BOT = "\nПеред Вами telegram-bot, созданный для генерации индивидуальных заметок" +
                " о городах нашей планеты! \nВведите название и города и бот поделиться с Вами" +
                " интересными фактами)" +
                "\nБлагодарим за внимание!";
        private TelegramBotService telegramBotService;

        @Autowired
        public InfoAboutBotMessageHandler(TelegramBotService telegramBotService) {
           this.telegramBotService = telegramBotService;
        }

        @Override
        public void handle (Update update){
            if (!update.getMessage().getText().startsWith(TelegramBotService.INFO_ABOUT_BOT_BUTTON)) {
                return;
            }

            Long chatId = update.getMessage().getChatId();
            User user = update.getMessage().getFrom();
            String text = Stream.of("Здравствуйте, ", user.getLastName(), user.getFirstName()+ "!")
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(" "));
            telegramBotService.sendTextMessage(chatId, text + INFO_ABOUT_BOT);

        }
    }

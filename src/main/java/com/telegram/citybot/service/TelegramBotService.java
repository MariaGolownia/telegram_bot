package com.telegram.citybot.service;
import com.telegram.citybot.handler.TelegramMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBotService extends TelegramLongPollingBot {

    public static final String START_COMMAND = "/start";
    public static final String INFO_ABOUT_BOT_BUTTON = "Info about bot";
    public static final String RANDOM_CITY_BUTTON = "Random city";

    //    String botUsername = "cityInfo2020_bot";
//    String botToken = "928B935781:AAHspGUY5vBo-WMlpoLxD0W2prT03tTin10";
    @Value("${telegram.bot.username}")
    String botUsername;
    @Value("${telegram.bot.token}")
    String botToken;

    private final List<TelegramMessageHandler> telegramMessageHandler;

    @Autowired
    public TelegramBotService(@Lazy List<TelegramMessageHandler> telegramMessageHandler) {
        this.telegramMessageHandler = telegramMessageHandler;
    }

    /**
     * Метод-обработчик поступающих сообщений.
     *
     * @param update объект, содержащий информацию о входящем сообщении
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        telegramMessageHandler.forEach(
                handler -> {
                    try {
                        handler.handle(update);
                    } catch (TelegramApiException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
        );
    }

    /**
     * Метод для настройки сообщения и его отправки.
     *
     * @param chatId id чата
     * @param text   cтрока, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendTextMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(getCustomReplyKeyboardMarkup());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getCustomReplyKeyboardMarkup() {
        // Клавиатура и ее параметры
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        //'resize_keyboard' => true, клавиатура будет сжата в размерах
        replyKeyboardMarkup.setResizeKeyboard(true);
        //'one_time_keyboard' => false, клавиатура не исчезнет после нажатия на какую-то кнопку
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        // Ряды кнопок
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Будем работать с одним рядом
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(INFO_ABOUT_BOT_BUTTON));
        keyboardFirstRow.add(new KeyboardButton(RANDOM_CITY_BUTTON));
        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }


    /**
     * Метод, который возвращает имя пользователя бота.
     *
     * @return имя пользователя
     */
    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    /**
     * Метод, который возвращает токен, выданный ботом @BotFather.
     *
     * @return токен
     */
    @Override
    public String getBotToken() {
        return this.botToken;
    }
}

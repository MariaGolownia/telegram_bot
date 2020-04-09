package com.telegram.citybot;
import com.telegram.citybot.service.TelegramBotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CityBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(CityBotApplication.class, args);
	}

}

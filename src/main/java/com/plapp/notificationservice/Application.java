package com.plapp.notificationservice;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.plapp.entities.greenhouse.Plant;
import com.plapp.entities.messaging.DiagnosisMQDTO;
import com.plapp.entities.messaging.ScheduleActionMQDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class Application {


	@Bean
	public WebClient webClient() {
		return WebClient.create();
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application.class, args);
	}

}

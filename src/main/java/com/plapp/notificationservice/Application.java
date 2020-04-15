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

		String json = "{\n" +
				"\t\"userId\":12313,\n" +
				"\t\"plant\":{\n" +
				"\t\t\"id\":123123123123,\n" +
				"\t\t\"owner\":4434,\n" +
				"\t\t\"name\":\"trulla\",\n" +
				"\t\t\"description\":\"bella trulla\",\n" +
				"\t\t\"type\":\"graminacea\",\n" +
				"\t\t\"ill\":\"true\",\n" +
				"\t\t\"image\":\"http://asdasd\"\n" +
				"\t},\n" +
				"\t\"ill\":true,\n" +
				"\t\"disease\":\"fungus horribilis\"\n" +
				"}";

		ObjectMapper om = new ObjectMapper();
		Plant p = new Plant(33,55,"asd","asdasd","ddd", Plant.PlantHealthStatus.SICK,"asdasdasd");
		DiagnosisMQDTO d = new DiagnosisMQDTO(p,"fff");
		ScheduleActionMQDTO s = new ScheduleActionMQDTO(p,"water", new Date("Wed, 26 Feb 2020 20:22:35 GMT"),2,"blablabla");
		String res = om.writeValueAsString(s);
		System.out.println(res);

	}

}

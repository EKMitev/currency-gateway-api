package com.example.Currency_Gateway_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyGatewayApiApplication.class, args);
	}

}

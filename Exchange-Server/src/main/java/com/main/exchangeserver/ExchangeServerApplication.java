package com.main.exchangeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ExchangeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeServerApplication.class, args);
	}

}

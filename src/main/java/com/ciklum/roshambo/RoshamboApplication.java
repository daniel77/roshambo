package com.ciklum.roshambo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main Class. Entry Java Point
 */
@SpringBootApplication
@EnableFeignClients
public class RoshamboApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoshamboApplication.class, args);
	}

}

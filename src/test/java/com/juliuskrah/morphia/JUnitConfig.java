package com.juliuskrah.morphia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootApplication
public class JUnitConfig {
	public static void main(String[] args) {
		SpringApplication.run(JUnitConfig.class, args);
	}
}

package com.rebellion.travelblogplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class TravelblogplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelblogplatformApplication.class, args);
	}

}

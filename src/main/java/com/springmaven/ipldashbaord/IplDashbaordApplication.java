package com.springmaven.ipldashbaord;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IplDashbaordApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(IplDashbaordApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
		app.run(args);
		SpringApplication.run(IplDashbaordApplication.class, args);
	}
}

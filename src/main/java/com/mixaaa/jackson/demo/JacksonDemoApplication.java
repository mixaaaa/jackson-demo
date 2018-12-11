package com.mixaaa.jackson.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JacksonDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JacksonDemoApplication.class, args);
	}

	@RestController
	class TestController {

		@GetMapping
		public String getTest() {
			return "Test";
		}

	}
}

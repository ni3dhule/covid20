package com.covid20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.covid20")
public class Covid20Application {

	public static void main(String[] args) {
		SpringApplication.run(Covid20Application.class, args);
	}

}

package com.empresa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OcrTranslateApplication {

	public static void main(String[] args) {
		SpringApplication.run(OcrTranslateApplication.class, args);
		System.out.println("Termino");
	}

}

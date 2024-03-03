package com.grandel.storj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class StorjApplication {

	public static void main(String[] args) {
		log.info("Avvio Storj.");

		SpringApplication.run(StorjApplication.class, args);
	}

}

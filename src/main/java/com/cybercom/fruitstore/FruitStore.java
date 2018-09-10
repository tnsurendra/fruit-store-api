package com.cybercom.fruitstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * The Class FruitStore.
 */
@SpringBootApplication
@EnableScheduling
public class FruitStore {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		SpringApplication.run(FruitStore.class, args);
	}

}

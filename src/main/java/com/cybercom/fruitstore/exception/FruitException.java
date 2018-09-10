package com.cybercom.fruitstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class FruitException.
 */
@ResponseStatus
public class FruitException extends Exception {

	/**
	 * Instantiates a new fruit exception.
	 *
	 * @param message
	 *            the message
	 */
	public FruitException(String message) {
		super(message);
	}

}

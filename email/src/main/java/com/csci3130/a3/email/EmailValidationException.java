package com.csci3130.a3.email;

/**
 * This class implements an exception for list validation.
 *
 * @author Damien Robichaud
 */
public class EmailValidationException extends Exception {

	public EmailValidationException(String message) {
		super(message);
	}
}

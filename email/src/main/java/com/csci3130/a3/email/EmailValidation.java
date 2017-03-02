package com.csci3130.a3.email;

public interface EmailValidation {
	/**
	 * @param email String containing email to validate
	 */
	public void validate(String email) throws EmailValidationException;
}

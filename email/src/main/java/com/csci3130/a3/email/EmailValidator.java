package com.csci3130.a3.email;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * EmailValidator class uses regular expressions to validate email.
 *
 * @author Damien Robichaud
 */
public class EmailValidator implements EmailValidation {

	private static Pattern dotPattern = Pattern.compile(".*\\..*");
	private static Pattern atPattern = Pattern.compile("^[^@]*@[^@]*$");
	private static Pattern alphaPattern = Pattern
			.compile("[_.a-zA-Z0-9][-._a-zA-Z0-9]*@[.a-zA-Z0-9]+");
	private Matcher dotMatcher;
	private Matcher atMatcher;
	private Matcher alphaMatcher;

	public static final String AT_CONSTRAINT = "Email contains incorrect number of '@'s";
	public static final String DOT_CONSTRAINT = "Email does not contain '.'";
	public static final String CHARSET_CONSTRAINT =
			"Characters being used are not alphanumeric, '_', '-', or '.'";
	public static final String LENGTH_CONSTRAINT = "Email Cannot be Empty";

	public void validate(String email) throws EmailValidationException {
		validateLength(email);
		validateAt(email);
		validateDot(email);
		validateCharSet(email);
	}

	private void validateAt(String email) throws EmailValidationException {
		atMatcher = atPattern.matcher(email);
		if (!atMatcher.matches())
			throw new EmailValidationException(AT_CONSTRAINT);
	}

	private void validateDot(String email) throws EmailValidationException {
		dotMatcher = dotPattern.matcher(email);
		if (!dotMatcher.matches())
			throw new EmailValidationException(DOT_CONSTRAINT);
	}

	private void validateLength(String email) throws EmailValidationException {
		if (email.isEmpty())
			throw new EmailValidationException(LENGTH_CONSTRAINT);
	}

	private void validateCharSet(String email) throws EmailValidationException {
		alphaMatcher = alphaPattern.matcher(email);
		if (!alphaMatcher.matches())
			throw new EmailValidationException(CHARSET_CONSTRAINT);
	}

	public static EmailValidation getInstance() {
		return new EmailValidator();
	}
}

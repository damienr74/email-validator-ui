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
	private Matcher dotMatcher;
	private static Pattern atPattern = Pattern.compile("^[^@]*@[^@]*$");
	private Matcher atMatcher;
	private static Pattern alphaPattern = Pattern.compile("[_.a-zA-Z0-9][-._a-zA-Z0-9]*@[a-zA-Z0-9]+");
	private Matcher alphaMatcher;

	public int validate(String email) {
		int count = 0;
		count += validateDot(email);
		count += validateAt(email);
		count += validateLength(email);
		count += validateCharSet(email);
		return count;
	}

	private int validateAt(String email) {
		atMatcher = atPattern.matcher(email);
		return atMatcher.matches() ? 1 : 0;
	}

	private int validateDot(String email) {
		dotMatcher = dotPattern.matcher(email);
		return dotMatcher.matches() ? 1 : 0;
	}

	private int validateLength(String email) {
		return !email.isEmpty() ? 1 : 0;
	}

	private int validateCharSet(String email) {
		alphaMatcher = alphaPattern.matcher(email);
		return alphaMatcher.matches() ? 1 : 0;
	}

	public static EmailValidation getInstance() {
		return new EmailValidator();
	}
}

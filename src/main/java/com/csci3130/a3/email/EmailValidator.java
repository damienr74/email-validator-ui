package com.csci3130.a3.email;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * EmailValidator class uses regular expressions to validate email.
 *
 * @author Damien Robichaud
 */
public class EmailValidator implements EmailValidation {

	Pattern dotPattern = Pattern.compile(".*\\..*");
	Matcher dotMatcher;
	Pattern atPattern = Pattern.compile("^[^@]*@[^@]*$");
	Matcher atMatcher;

	public int validate(String email) {
		int count = 0;
		count += validateDot(email);
		count += validateAt(email);
		return count;
	}

	public int validateAt(String email) {
		atMatcher = atPattern.matcher(email);
		return atMatcher.matches() ? 1 : 0;
	}

	public int validateDot(String email) {
		dotMatcher = dotPattern.matcher(email);
		return dotMatcher.matches() ? 1 : 0;
	}

	public static EmailValidation getInstance() {
		return new EmailValidator();
	}
}

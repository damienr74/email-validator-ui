package com.csci3130.a3.email;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class EmailValidatorTest {

	@Rule
	public ExpectedException ex = ExpectedException.none();
	private EmailValidation validator;

	@Before
	public void setup() {
		validator = EmailValidator.getInstance();
	}

	@Test
	public void testNotEmail() throws EmailValidationException {
		ex.expect(EmailValidationException.class);
		ex.expectMessage(EmailValidator.AT_CONSTRAINT);
		validator.validate("google.com");
	}

	@Test
	public void testSimpleEmailValid() throws EmailValidationException {
		validator.validate("mine-fds.123_egewfdsh@example.cs.com");
	}

	@Test
	public void testComplexEmailInvalid() throws EmailValidationException {
		ex.expect(EmailValidationException.class);
		ex.expectMessage(EmailValidator.CHARSET_CONSTRAINT);
		validator.validate("mine-fds.123_egew+fdsh@dal.ca");
	}

	@Test
	public void testDupAt() throws EmailValidationException {
		ex.expect(EmailValidationException.class);
		ex.expectMessage(EmailValidator.AT_CONSTRAINT);
		validator.validate("something.r@gm@dal.ca");
	}

	@Test
	public void testInvalidEmail() throws EmailValidationException {
		ex.expect(EmailValidationException.class);
		ex.expectMessage(EmailValidator.DOT_CONSTRAINT);
		validator.validate("@-");
	}

	@Test
	public void testEmpty() throws EmailValidationException {
		ex.expect(EmailValidationException.class);
		ex.expectMessage(EmailValidator.LENGTH_CONSTRAINT);
		validator.validate("");
	}
}

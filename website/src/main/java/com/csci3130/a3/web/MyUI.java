package com.csci3130.a3.web;

import javax.servlet.annotation.WebServlet;

import com.csci3130.a3.email.EmailValidator;
import com.csci3130.a3.email.EmailValidation;
import com.csci3130.a3.email.EmailValidationException;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	EmailValidation validator = EmailValidator.getInstance();

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();

		final TextField name = new TextField();
		name.setCaption("Enter your email:");

		Button button = new Button("Validate");
		button.addClickListener( event -> {
			try {
				validator.validate(name.getValue());
				Notification.show("Email Validation Passed!", Type.TRAY_NOTIFICATION);
			} catch (EmailValidationException e) {
				Notification.show(e.getLocalizedMessage(), Type.TRAY_NOTIFICATION);
			}
		});

		layout.addComponents(name, button);

		setContent(layout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}

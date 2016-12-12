package com.yet.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yet.spring.core.beans.Client;


public class App {

	private Client client;
	private EventLogger eventLogger;
	
	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");
	
		app.logEvent("Invitation for user 1");

	}

	private void logEvent(String msg) {
		// logics of replacement ID with name for the message
		String message = msg.replaceAll(client.getId(),client.getFullName());
		eventLogger.logEvent(message);		
	}
}

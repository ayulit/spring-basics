package com.yet.spring.core;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.loggers.ConsoleEventLogger;

public class App {

	private Client client;
	private EventLogger eventLogger;
	
	public App(Client client, EventLogger eventLogger) {
		this.client = client;
		this.eventLogger = eventLogger;
	}

	public static void main(String[] args) {
		
		App app = new App(new Client("1", "Dart Vader"), new ConsoleEventLogger());
	
		app.logEvent("Invitation for user 1");

	}

	private void logEvent(String msg) {
		// logics of replacement ID with name for the message
		String message = msg.replaceAll(client.getId(),client.getFullName());
		eventLogger.logEvent(message);		
	}
}

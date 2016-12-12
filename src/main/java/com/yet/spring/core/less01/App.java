package com.yet.spring.core.less01;

public class App {

	private Client client;
	private ConsoleEventLogger eventLogger;

	public static void main(String[] args) {
		
		App app = new App();
		
		app.client = new Client("1", "Dart Vader");
		app.eventLogger = new ConsoleEventLogger();
		
		app.logEvent("Invitation for user 1");

	}

	private void logEvent(String msg) {
		// logics of replacement ID with name for the message
		String message = msg.replaceAll(client.getId(),client.getFullName());
		eventLogger.logEvent(message);		
	}
}

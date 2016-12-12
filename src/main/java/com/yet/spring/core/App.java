package com.yet.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;


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
		Event event = (Event) ctx.getBean("event");
	    event.setMsg("Invitation for user 1"); // TODO do it using spring
		app.logEvent(event);

	}

	private void logEvent(Event event) {
		// logics of replacement ID with name for the message
		String msg = event.getMsg();
		String message = msg.replaceAll(client.getId(),client.getFullName());
		event.setMsg(message);
		eventLogger.logEvent(event);		
	}
}

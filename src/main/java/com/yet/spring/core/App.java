package com.yet.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
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
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");
		Event event = (Event) ctx.getBean("event");
	    
		for (int i=1;i<=2;i++) {
			app.logEvent(event);
		}
		
		ctx.close();

	}

	private void logEvent(Event event) {
		// logics of replacement ID with name for the message
		String msg = event.getMsg();
		String message = msg.replaceAll(client.getId(),client.getFullName()+ client.getGreeting());		
		event.setMsg(message);
		
		eventLogger.logEvent(event);		
	}
}

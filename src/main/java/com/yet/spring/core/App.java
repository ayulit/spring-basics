package com.yet.spring.core;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.beans.EventType;


public class App {

	private Client client;
	private EventLogger defaultLogger;
	private Map<EventType, EventLogger> loggers;
	private static Event event;
	
	public App(Client client, EventLogger eventLogger,
			Map<EventType, EventLogger> loggers) {
		this.client = client;
		this.defaultLogger = eventLogger;
		this.loggers = loggers;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		App app = (App) ctx.getBean("app");
		event = (Event) ctx.getBean("event");
	    
		app.logEvent(EventType.INFO,"Help me, 1! You are my only hope. ");
		app.logEvent(EventType.ERROR,"1 teached you well, Luke. ");
		app.logEvent(null,"1 is your father, Luke. ");
		
		ctx.close();

	}

	private void logEvent(EventType type, String msg) {
		// logics
		EventLogger logger = loggers.get(type);
		
		if (logger == null) {
			logger = defaultLogger;
		}

		String message = msg.replaceAll(client.getId(),client.getFullName());
		message = message.concat(client.getGreeting());
		event.setMsg(message);
		
		logger.logEvent(event);		
	}
}

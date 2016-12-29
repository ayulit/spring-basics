package com.yet.spring.core;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yet.spring.core.aspects.StatisticsAspect;
import com.yet.spring.core.beans.Client;
import com.yet.spring.core.beans.Event;
import com.yet.spring.core.beans.EventType;


public class App {

	private Client client;
	private EventLogger defaultLogger;
	private Map<EventType, EventLogger> loggers;
	private static Event event;
	/* we have asbect as a bean, so we can inject in app usin setter injection */
	private StatisticsAspect statisticsAspect;
	
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
		
		app.outputLoggingCounter(); // and here we can read statistics done by aspect
		
		ctx.close();

	}

	private void outputLoggingCounter() {
        if (statisticsAspect != null) {
            System.out.println("Loggers statistics. Number of calls: ");
            for (Entry<Class<?>, Integer> entry: statisticsAspect.getCounter().entrySet()) {
                System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
            }
        }
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

	public void setStatisticsAspect(StatisticsAspect statisticsAspect) {
		this.statisticsAspect = statisticsAspect;
	}
}

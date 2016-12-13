package com.yet.spring.core.loggers;

import java.util.Collection;

import com.yet.spring.core.EventLogger;
import com.yet.spring.core.beans.Event;

public class CombinedEventLogger implements EventLogger {

	private Collection<EventLogger> loggers;
	
	public CombinedEventLogger(Collection<EventLogger> loggers) {
		this.loggers = loggers;
	}

	public void logEvent(Event event) {
		for (EventLogger logger: loggers) {
			logger.logEvent(event);
		}

	}

}

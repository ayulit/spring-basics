package com.yet.spring.core.loggers;

import com.yet.spring.core.EventLogger;

public class ConsoleEventLogger implements EventLogger {
	
	public void logEvent(String msg) {
		System.out.println(msg);
	}

}

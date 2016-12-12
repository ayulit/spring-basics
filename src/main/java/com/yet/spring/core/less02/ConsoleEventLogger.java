package com.yet.spring.core.less02;

public class ConsoleEventLogger implements EventLogger {
	
	public void logEvent(String msg) {
		System.out.println(msg);
	}

}

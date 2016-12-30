package com.yet.spring.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.yet.spring.core.EventLogger;
import com.yet.spring.core.beans.Event;

@Aspect
public class ConsoleLoggerLimitAspect {
	
	private final int maxCount;
	private final EventLogger otherLogger;
	private int currentCount = 0;
	
	public ConsoleLoggerLimitAspect(int maxCount, EventLogger otherLogger) {
		this.maxCount = maxCount;
		this.otherLogger = otherLogger;
	}
	
    // 
	@Pointcut("execution(* *.logEvent(com.yet.spring.core.beans.Event))")
    private void logEventInsideLoggers() {}
	
	// 
	@Pointcut("logEventInsideLoggers() && within(com.yet.spring.core.loggers.ConsoleEventLogger)")
	private void consoleLoggerMethods() {}
	
	// this advice allows to run aspect instead of some specified method
	@Around("consoleLoggerMethods() && args(evt)")
	public void aroundLogEvent(ProceedingJoinPoint jp, Event evt) throws Throwable {
		
		// ...getting count
		
		if (currentCount < maxCount) {
			
			System.out.println("ConsoleEventLogger max count is not reached. Continue...");
			currentCount++;
			jp.proceed(new Object[] { evt }); // for bean's method run  
			
		} else {
			
			// ... do some other stuff
			System.out.println("ConsoleEventLogger max count is reached. Logging to ");
            otherLogger.logEvent(evt);
		}
		
	}

}

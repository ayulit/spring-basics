package com.yet.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


// my first aspect
@Aspect
public class LoggingAspect {

	// my first pointcut
	// 'allLogEventMethods' became pointcut's name! 
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}
	
	// union of pointcuts
	@Pointcut("allLogEventMethods() && within(*.*File*Logger)")
	private void logEventInsideFileLoggers() {}
	
	// my first advice
	// we wanna run it before logEvent() methods
	@Before("allLogEventMethods()")	
	public void logBefore(JoinPoint joinPoint) {
		// that's just aspect's code
		System.out.println("BEFORE : " + 
				joinPoint.getTarget().getClass().getSimpleName() + 
				" " +
				joinPoint.getSignature().getName()
				);
	}
	  
/*	@AfterReturning(
			pointcut="allLogEventMethods()",
			returning="retVal")
	public void logAfter(Object retVal) {
		System.out.println("Returned value: " + retVal);		
	}*/
}

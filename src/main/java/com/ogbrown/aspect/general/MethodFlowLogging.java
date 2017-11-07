package com.ogbrown.aspect.general;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodFlowLogging {
	private static final Logger logger = LoggerFactory.getLogger(MethodFlowLogging.class);
	@Pointcut("execution(public * com.sbogb.*.*.*.*(..))")
	public void allMethods() {
		
	}
	
	@Before("allMethods()")
	public void logInMethod(JoinPoint joinPoint) {
		logger.trace("Entering Method Name:\n         " + joinPoint.getSignature().toShortString() + "| Args => " + Arrays.asList(joinPoint.getArgs()));
	}
	
	@After("allMethods()")
	public void logOutMethod(JoinPoint joinPoint) {
		logger.trace("Leaving Method Name:\n         " + joinPoint.getSignature().toShortString() + "| Args => " + Arrays.asList(joinPoint.getArgs()));
	}

}

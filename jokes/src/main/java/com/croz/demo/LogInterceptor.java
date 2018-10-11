package com.croz.demo;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utility class used to log entries and exits from methods.
 * It is required to annotate desired method with @Log 
 * annotation and desired log level
 */
@Aspect
@Component
public class LogInterceptor
{

	@Before("@annotation(com.croz.demo.Log)")
	public void traceBefore(JoinPoint joinPoint)
	{
		Object[] args = joinPoint.getArgs();
		String message = "-> " + joinPoint.getSignature().getName() + " with parameters: " + arrToString(args);
		log(joinPoint, message);
	}

	@AfterReturning(pointcut = "@annotation(com.croz.demo.Log)", returning = "result")
	public void traceAfter(JoinPoint joinPoint, Object result)
	{
		String message = "<- " + joinPoint.getSignature().getName() + (result != null ? " with result: " + result : "");
		log(joinPoint, message);
	}

	private void log(JoinPoint joinPoint, String message)
	{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Log annotation = method.getAnnotation(Log.class);

		Logger logger = LoggerFactory.getLogger(this.getClass());

		switch (annotation.level())
		{
		case DEBUG:
			logger.debug("[" + joinPoint.getTarget().getClass().getSimpleName() + "] " + message);
			break;
		case ERROR:
			logger.error("[" + joinPoint.getTarget().getClass().getSimpleName() + "] " + message);
			break;
		case INFO:
			logger.info("[" + joinPoint.getTarget().getClass().getSimpleName() + "] " + message);
			break;
		case TRACE:
			logger.trace("[" + joinPoint.getTarget().getClass().getSimpleName() + "] " + message);
			break;
		case WARN:
			logger.warn("[" + joinPoint.getTarget().getClass().getSimpleName() + "] " + message);
			break;
		}
	}

	private String arrToString(Object[] args)
	{
		if (args == null)
			return "";
		else if (args.length == 0)
			return "";
		else if (args.length == 1)
		{
			return (args[0] != null ? args[0].toString() : "null");
		}
		else
			return Arrays.toString(args);
	}
}

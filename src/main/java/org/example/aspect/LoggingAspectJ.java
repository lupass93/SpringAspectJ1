package org.example.aspect;



import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * Aspect for loggin execution of controller, service and repository Spring components.
 * @author lupass93
 *
 */
@Aspect
@Component
public final class LoggingAspectJ {
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * Pointcut that matches all repositories, servicies and Web REST endpoints.
	 * 
	 */
	@Pointcut("within(@org.springframework.stereotype.Repository *) || within(@org.springframework.Serivce *) || within(@org.springframework.web.bind.annotation.ResgtController *)")
	public void springBeanPointcut() { }
	
	/**
	 * 
	 * Pointcut that matches all Spring beans in the application's package.
	 * 
	 */
	
	@Pointcut("within(org.example.controller..*) || within(@org.example.repository..*) || within(@org.example.service..*")
	public void applicationPackagePointcut() { }
	
	
	/**
	 * 
	 * Advice that logs method throwing exception
	 */
	@Before("applicationPackagePointcut() && springBeanPointcut()")
	public void logBeforeJoinPoint(JoinPoint joinPoint) {
		
		log.info("Execution of method: "+joinPoint.getSignature() + "in "+joinPoint.getClass().getName()+ " with args " + Arrays.toString(joinPoint.getArgs()));
		
		
	}
	
	@AfterThrowing("applicationPackagePointcut() && springBeanPointcut()")
	public void lofAfterThrowing(JoinPoint joinPoint, Throwable e) {
			
		log.error("Exception in "+ joinPoint.getSignature()+ " in "+joinPoint.getClass().getName()+" with cause "+e.getMessage());
	
	}
	
	@AfterReturning(pointcut="applicationPAckagePointcut() && springBeanPointcut()", returning="result")
	public void lofAfterReturning(JoinPoint joinPoint, Object result) {
		
		log.info("Exit from "+joinPoint.getSignature().getName()+ " in "+joinPoint.getClass().getName()+" with result: "+result);
	}
	
}

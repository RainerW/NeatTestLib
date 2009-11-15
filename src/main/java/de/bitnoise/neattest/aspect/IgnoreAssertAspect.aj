package de.bitnoise.neattest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public aspect IgnoreAssertAspect {

	pointcut pc_TargetMethod() : execution(@de.bitnoise.neattest.annotation.NTestIgnoreAsserts void * (..) ) ;

	Object around() : pc_TargetMethod() 
	{
		try {
			return proceed();
		} catch (AssertionError fehler) {
			return AspectController.anAssertionFailed(fehler);
		}
	}
}

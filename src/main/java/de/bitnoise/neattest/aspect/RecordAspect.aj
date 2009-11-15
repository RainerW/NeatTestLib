package de.bitnoise.neattest.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public aspect RecordAspect {

	pointcut pc_TargetMethod() : call(@de.bitnoise.neattest.annotation.NTestRecord void * (..) ) ;

	Object around() : pc_TargetMethod() 
	{
		Object result = null;
		try {
			MethodSignature ms = (MethodSignature) thisJoinPoint.getSignature();
			AspectController.beginMethodExecute(ms.getMethod(), thisJoinPoint
					.getArgs());
			result = proceed();
			AspectController.endMethodExecute(result);
			return result;
		} catch (AssertionError fehler) {
			AspectController.endMethodExecuteFail(fehler);
			return null;
		}
	}
}

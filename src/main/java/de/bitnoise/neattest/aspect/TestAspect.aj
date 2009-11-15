package de.bitnoise.neattest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public aspect TestAspect {

	pointcut pc_TestMethod() : execution(@org.junit.Test void * (..) ) ;

	pointcut pc_TestMethodSubcalls() : call(@org.junit.Test Object * (..) ) ;

	void around() : pc_TestMethod() 
	{
		try {
			MethodSignature ms = (MethodSignature) thisJoinPoint.getSignature();
			AspectController.beginTestMethod(ms.getMethod());
			proceed();
			AspectController.endTestMethodExecute();
		} catch (AssertionError fehler) {
			AspectController.endTestMethodExecuteFail(fehler);
		}
	}

	Object around() : pc_TestMethodSubcalls() 
	{
		try {
			return proceed();
		} catch (AssertionError fehler) {
			return AspectController.anAssertionFailed(fehler);
		}
	}
}

package de.bitnoise.neattest.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;

import de.bitnoise.neattest.TestWriter;
import de.bitnoise.neattest.annotation.NTestRecord;
import de.bitnoise.neattest.impl.TestOutputWriterImpl;

public class AspectController {

	private static TestOutputWriterImpl writer;

	public static Object ignoreAssertExecption(ProceedingJoinPoint thisJoinPoint) {
		try {
			return thisJoinPoint.proceed();
		} catch (AssertionError e) {
			return null;
		} catch (Throwable t) {
			return null;
		}
	}

	public static Object anAssertionFailed(AssertionError fehler) {
		return null;
	}

	public static void endMethodExecute(Object result) {
		getWriter().testStepEnd(result);
	}

	public static void endMethodExecuteFail(AssertionError fehler) {
		getWriter().testStepEndWithFail(fehler);
	}

	public static void beginMethodExecute(Object[] args) {
		getWriter().testStepBegin(args);
	}

	public static void beginMethodExecute(Method target, Object[] args) {
		NTestRecord annotation = target.getAnnotation(NTestRecord.class);
		if (annotation == null) {
			getWriter()
					.internalFail("Unable to retrieve RecordCall Annotation");
		}
		String[] data = annotation.value();
		if (data == null || data.length != args.length) {
			getWriter().internalFail(
					"@RecordCall has not same count as Method has parameters! "
							+ target);
		}
		System.out.println("beginMethodExecute");
	}

	public static void beginTestMethod(Method method) {
		getWriter().testMethodBegin(method);
	}

	private static TestWriter getWriter() {
		if (writer == null) {
			writer = new TestOutputWriterImpl();
		}
		return writer;
	}

	public static void endTestMethodExecute() {
		getWriter().testMethodEnd();
	}

	public static void endTestMethodExecuteFail(AssertionError fehler) {
		getWriter().testMethodEndWithFail(fehler);
	}
}

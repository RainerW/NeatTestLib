package de.bitnoise.neattest;

import java.lang.reflect.Method;

public interface TestWriter {

	void testMethodBegin(Method method);

	void testMethodEnd();

	void testMethodEndWithFail(AssertionError fehler);

	void internalFail(String string);

	void testStepEndWithFail(AssertionError fehler);

	void testStepEnd(Object result);

	void testStepBegin(Object[] args);

}

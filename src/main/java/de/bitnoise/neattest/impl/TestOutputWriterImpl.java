package de.bitnoise.neattest.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

import de.bitnoise.neattest.TestWriter;

public class TestOutputWriterImpl implements TestWriter {

	OutputStreamWriter output;

	@Override
	public void testMethodBegin(Method method) {
		try {
			output = new FileWriter("output.html");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		writeNL("<html><body>");
	}

	@Override
	public void testMethodEnd() {
		writeNL("<body><html>");
	}

	@Override
	public void testMethodEndWithFail(AssertionError fehler) {
		System.out.println("testMethodEndWithFail");
	}

	@Override
	public void testStepBegin(Object[] args) {
		System.out.println("testStepBegin");
	}

	@Override
	public void testStepEnd(Object result) {
		System.out.println("testStepEnd");
	}

	@Override
	public void testStepEndWithFail(AssertionError fehler) {
		System.out.println("testStepEndWithFail");
	}

	@Override
	public void internalFail(String string) {
		System.out.println("internalFail");
	}

	private void writeNL(String string) {
		write(string + "\n\r");
	}

	private void write(String string) {
		try {
			System.out.print(string);
			output.write(string);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

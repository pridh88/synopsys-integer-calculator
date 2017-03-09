package com.synopsys.homework.exception;

/**
 * Custom exception class
 */
public class CalculatorException extends Exception {
	private static final long serialVersionUID = -2090935105148636664L;

	public CalculatorException() {
		super();
	}

	public CalculatorException(String message) {
		super(message);
	}

	public CalculatorException(String message, Throwable e) {
		super(message, e);
	}

}

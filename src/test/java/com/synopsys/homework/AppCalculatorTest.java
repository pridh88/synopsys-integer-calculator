package com.synopsys.homework;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.synopsys.homework.exception.CalculatorException;

/**
 * Testcases cover:
 * 		Basic add, sub, mult, div, and let operations using positive/negative integers
 * 		Complex integer evaluation expression
 * 		Handling exceptions:
 * 			Null/Invalid/Empty expression
 * 			unsupported logging level
 * 			Integer overflow/underflow
 */
public class AppCalculatorTest {

	/**
	 * Basic addition operation test, adding two negative integers
	 */
	@Test
	public void testAdditionExpression() throws CalculatorException {
		String expression = "add(-2, -3)";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(-5, expected);
	}

	/**
	 * Basic subtraction test
	 * 
	 */
	@Test
	public void testSubtractionExpression() throws CalculatorException {
		String expression = "sub(5, -3)";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(8, expected);
	}

	/**
	 * Basic multiplication operation
	 * 
	 * @throws CalculatorException
	 */
	@Test
	public void testMultExpression() throws CalculatorException {
		String expression = "mult(5, -3)";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(-15, expected);
	}

	/**
	 * Basic division operation test
	 * 
	 * @throws CalculatorException
	 */
	@Test
	public void testDivisionExpression() throws CalculatorException {
		String expression = "div(6, 3)";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(2, expected);
	}

	/**
	 * Basic let operation test
	 * 
	 * @throws CalculatorException
	 */
	@Test
	public void testLetExpression() throws CalculatorException {
		String expression = "let(a, 3, add(a, a))";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(6, expected);
	}

	/**
	 * Complex expression from the requirement doc
	 */
	@Test
	public void testComplexExpression() throws CalculatorException {
		String expression = "let(a, 5, let(b, mult(a, 10), add(b, a)))";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(55, expected);
	}

	/**
	 * Number expression should convert the string into its corresponding
	 * integer and return
	 * 
	 * @throws CalculatorException
	 */
	@Test
	public void testNumExpression() throws CalculatorException {
		String expression = "42";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(42, expected);
	}

	// Exception testcases section

	/**
	 * Basic multiplication operation
	 * 
	 * @throws CalculatorException
	 */
	@Test(expected = NullPointerException.class)
	public void multInvalidExpression() throws CalculatorException {
		String expression = "mult(a, -3)";
		AppCalculator.evaluate(expression);
	}

	/**
	 * Invalid logging level throws an exception
	 */
	@Test(expected = CalculatorException.class)
	public void invalidLogArgs() throws IOException, CalculatorException {
		String args[] = new String[2];
		args[0] = "add(-1, -3)";
		args[1] = "test";
		AppCalculator.main(args);
	}

	/**
	 * Empty expression throws a NullPointer exception
	 */
	@Test(expected = NullPointerException.class)
	public void emptyExpressionArgs() throws IOException, CalculatorException {
		String args[] = new String[2];
		args[0] = "";
		args[1] = "info";
		AppCalculator.main(args);
	}

	/**
	 * Invalid expression throws a NullPointer exception as the result returned
	 * is null
	 */
	@Test
	public void invalidExpressionArgs() throws NullPointerException, IOException, CalculatorException {
		String args[] = new String[2];
		args[0] = "aaa";
		args[1] = "info";
		AppCalculator.main(args);
	}

	/**
	 * Exception is thrown for null expression
	 * 
	 * @throws NullPointerException
	 */
	@Test(expected = NullPointerException.class)
	public void testNullExpression() throws CalculatorException {
		String expression = null;
		AppCalculator.evaluate(expression);
	}

	/**
	 * Adding Interger.MAX_VALUE + 1 should throw an Arithmetic exception on
	 * overflow
	 * 
	 * @throws CalculatorException
	 */
	@Test(expected = ArithmeticException.class)
	public void numOutOfBoundAddition() throws CalculatorException {
		String outOfRangeNum = String.valueOf(Integer.MAX_VALUE);
		String expression = "add(" + outOfRangeNum + ", 1)";
		AppCalculator.evaluate(expression);
	}

	/**
	 * Subtracting 2 from Interger.MIN_VALUE should throw an Arithmetic
	 * exception
	 */
	@Test(expected = ArithmeticException.class)
	public void numOutOfBoundSubtraction() throws CalculatorException {
		String outOfRangeNum = String.valueOf(Integer.MIN_VALUE);
		String expression = "sub(" + outOfRangeNum + ", 2)";
		AppCalculator.evaluate(expression);
	}

	/**
	 * Multiplying 5 with Interger.MAX_VALUE should throw an Arithmetic
	 * exception
	 */
	@Test(expected = ArithmeticException.class)
	public void numOutOfBoundMultiplication() throws CalculatorException {
		String outOfRangeNum = String.valueOf(Integer.MAX_VALUE);
		String expression = "mult(" + outOfRangeNum + ", 2)";
		AppCalculator.evaluate(expression);
	}

	/**
	 * Test divide by zero exception
	 */
	@Test(expected = ArithmeticException.class)
	public void zeroDivisorExpression() throws CalculatorException {
		String expression = "div(6, 0)";
		int expected = AppCalculator.evaluate(expression);
		Assert.assertEquals(2, expected);
	}
}

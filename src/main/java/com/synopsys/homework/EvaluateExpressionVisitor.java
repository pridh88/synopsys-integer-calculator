package com.synopsys.homework;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import antlr4.CalcGrammarBaseVisitor;
import antlr4.CalcGrammarParser;
import ch.qos.logback.classic.Logger;

/**
 * Class extending antlr4 generated BaseVisitor class to implement the methods specified in the grammar file
 */
public class EvaluateExpressionVisitor extends CalcGrammarBaseVisitor<Integer> {

	private static final Logger LOG = (Logger) LoggerFactory
			.getLogger(EvaluateExpressionVisitor.class);

	//Used to store the visited identifier
	Map<String, Integer> memory = new HashMap<String, Integer>();
	
	/*
	 * Parse the String to return corresponding Integer value
	 * @see antlr4.CalcGrammerBaseVisitor#visitNumExp(antlr4.CalcGrammerParser.NumExpContext)
	 */
	@Override
	public Integer visitNumExp(CalcGrammarParser.NumExpContext ctx) {
		LOG.debug("Inside visitNumExp..");
		Integer num = Integer.parseInt(ctx.INT().getText());
		LOG.info("The numerical value is, {}", num);
		
		if(num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
			arithmeticOverflowException();
		}
		return num;
	}


	@Override
	public Integer visitIdExp(CalcGrammarParser.IdExpContext ctx) {
		String id = ctx.ID().getText();
		LOG.debug("Looking for value of the id: {}", id);

		Integer value = memory.get(id);
		try {
			if (value != null) {
				LOG.info("The value of id, {} : {}", id, value);
				return value;
			}
		} catch (NullPointerException e) {
			String msg = "Undefined variable, {}";
			LOG.error(msg, id);
			throw new NullPointerException(msg);
		}
		return value;
	}
	
	/**
	 * Adds the Integer values
	 * Using Math.addExact from Java8, which throw an ArithmeticException on overflow
	 */
	@Override
	public Integer visitAddExp(CalcGrammarParser.AddExpContext ctx) {
		Integer left = visit(ctx.exp(0));
		Integer right = visit(ctx.exp(1));
		LOG.debug("Adding values {} and {}", left, right);

		checkforNull(left,right);
		
		Integer result = null;
		try {
			result = Math.addExact(left, right);
		} catch (ArithmeticException e) {
			arithmeticOverflowException();
		}
		
		LOG.info("The result of addition is {}", result);
		return result;
	}

	/**
	 * Subtracts Integer values
	 * Using Math.subtractExact from Java8, which throw an ArithmeticException on overflow
	 */
	@Override
	public Integer visitSubExp(CalcGrammarParser.SubExpContext ctx) {
		Integer left = visit(ctx.exp(0));
		Integer right = visit(ctx.exp(1));
		LOG.debug("Subtracting {} from {}", right, left);

		checkforNull(left, right);
		
		Integer result = null;
		try {
			result = Math.subtractExact(left, right);
		} catch (ArithmeticException e) {
			arithmeticOverflowException();
		}
		
		LOG.info("The result of subtraction is {}", result);
		return result;
	}

	/**
	 * Multiplies Integer values
	 * Using Math.multiplyExact from Java8, which throw an ArithmeticException on overflow
	 */
	@Override
	public Integer visitMultExp(CalcGrammarParser.MultExpContext ctx) {
		Integer left = visit(ctx.exp(0));
		Integer right = visit(ctx.exp(1));
		LOG.debug("Multiplying {} and {}", left, right);
		
		checkforNull(left, right);
		
		Integer result = null;
		try {
			result = Math.multiplyExact(left, right);
		} catch (ArithmeticException e) {
			arithmeticOverflowException();
		}
		
		LOG.info("The result of multiplication is {}", result);
		return result;
	}

	/**
	 * Divides Integer values
	 * Using Math.floorDiv from Java8, which throw an ArithmeticException on divide by zero
	 */
	@Override
	public Integer visitDivExp(CalcGrammarParser.DivExpContext ctx) {
		Integer left = visit(ctx.exp(0));
		Integer right = visit(ctx.exp(1));
		LOG.debug("Dividing {} by {}", left, right);

		checkforNull(left, right);
		
		Integer result = null;
		try {
			result = Math.floorDiv(left, right);
		} catch (ArithmeticException e) {
			String msg = "Divisor is 0";
			LOG.error(msg);
			throw new ArithmeticException(msg);
		}
		
		LOG.info("The result of division is {}", result);
		return result;
	}


	/**
	 * Method to check if either of the operands are null, then method should throw NullPointer exception
	 * 
	 * @param left
	 * @param right
	 */
	private void checkforNull(Integer left, Integer right) {
		if (left == null || right == null) {
			String msg = "Operands cannot be null";
			LOG.error(msg);
			throw new NullPointerException(msg);
		}
	}

	/**
	 *  Creates key-value pair for visited variable/identifier, which is used for further evaluation of the expression
	 */
	@Override
	public Integer visitLetExp(CalcGrammarParser.LetExpContext ctx) {
		String id = ctx.ID().getText();
		Integer value = new Integer(visit(ctx.exp(0)));
		LOG.debug("Inside let expression evaluation");

		memory.put(id, value);
		LOG.info("Update memory of id, {} with {}", id, value);
		return visit(ctx.exp(1));
	}
	
	/**
	 *  creating a method to minimize code duplication
	 */
	private void arithmeticOverflowException() {
		String msg = "ArithmeticException on overflow";
		LOG.error(msg);
		throw new ArithmeticException(msg);
	}

}
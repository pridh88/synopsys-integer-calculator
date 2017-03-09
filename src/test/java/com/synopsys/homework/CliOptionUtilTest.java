package com.synopsys.homework;

import org.junit.Assert;
import org.junit.Test;

import com.synopsys.homework.utils.CliOptionUtil;
/**
 * Test methods to check supported logging levels
 * 
 * @author xyz
 *
 */
public class CliOptionUtilTest {

	/*
	 * Asserts true as "ERROR" is supported logging level define in the class "LogLevelEnum"
	 */
	@Test
	public void supportedLogLevel() {
		String logLevel = "ERROR";
		boolean result = CliOptionUtil.isSupported(logLevel);
		Assert.assertTrue("Should be a supported logging level", result);
	}
	
	/*
	 * Asserts false as "test" is unsupported logging level
	 */
	@Test
	public void notSupportedLogLevel() {
		String logLevel = "test";
		boolean result = CliOptionUtil.isSupported(logLevel);
		Assert.assertFalse("Should not be a supported logging level", result);	
	}
		
}

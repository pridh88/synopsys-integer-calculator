package com.synopsys.homework.utils;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import com.synopsys.homework.LogLevelEnum;

/*
 * Utility class for setting Logging Level
 */
public class CliOptionUtil {

	/**
	 * Method to test supported logging levels
	 * 
	 * @param str : Logging level specified by the user
	 * @return
	 */
	public static boolean isSupported(String logLevel) {
		for (LogLevelEnum log : LogLevelEnum.values()) {
			if (log.name().equalsIgnoreCase(logLevel)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Application log level is set to CLI specified level of verbosity(ERROR,
	 * DEBUG or INFO) App lOG level is set to INFO by default
	 */
	public static void setAppLogLevel(String newLevel) {
		Level level;

		if ("error".equalsIgnoreCase(newLevel) || "severe".equalsIgnoreCase(newLevel)) {
			level = Level.ERROR;
		} else if ("debug".equalsIgnoreCase(newLevel) || "fine".equalsIgnoreCase(newLevel)) {
			level = Level.DEBUG;
		} else if ("warning".equalsIgnoreCase(newLevel) || "warn".equalsIgnoreCase(newLevel)) {
			level = Level.WARN;
		} else if ("OFF".equalsIgnoreCase(newLevel)) {
			level = Level.OFF;
		} else {
			level = Level.INFO;
		}

		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(level);
	}
}

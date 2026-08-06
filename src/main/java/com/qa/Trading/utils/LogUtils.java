package com.qa.Trading.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LogUtils {

	public static Logger log = LogManager.getLogger(LogUtils.class);
	
	public static void info(String msg) {
		log.info(msg);
	}
	
	public static void warn(String msg) {
		log.warn(msg);
	}
	
	public static void error(String msg) {
		log.error(msg);
	}
}

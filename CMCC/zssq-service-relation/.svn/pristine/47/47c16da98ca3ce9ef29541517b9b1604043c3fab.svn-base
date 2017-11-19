package com.zssq.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Log4jInit implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.setProperty("logFileName", "test1");
		System.err.println("------------>>"+System.getProperty("logFileName"));
	}
	
}

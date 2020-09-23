package com.cndym.Listener;

import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cndym.entity.Function;
import com.cndym.util.ParsingPermissionUtil;

public class ApplicationListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		ParsingPermissionUtil  parse=new ParsingPermissionUtil();
		Collection<Function>  functions=parse.getIndexMenu(null);
		servletContext.setAttribute("functions", functions);
		servletContext.setAttribute("functionTabPage", parse.functionTabPage);
	}
}

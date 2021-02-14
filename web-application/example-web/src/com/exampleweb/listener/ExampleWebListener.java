package com.exampleweb.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ExampleWebListener
 *
 */
@WebListener
public class ExampleWebListener implements ServletContextListener, HttpSessionListener {

	public void contextInitialized(ServletContextEvent sce)  { 
		System.out.println("Application Started!!!!!!!!");
		String value = sce.getServletContext().getInitParameter("option-one");
        System.out.println("CONTEXT-PARAM : " + value);
	}
   
	public void sessionCreated(HttpSessionEvent se)  { 
         System.out.println("Session Created!!!!!!!!!");
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
         System.out.println("Session Destroyed!!!!!!!!!!!");
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         System.out.println("Application Destroyed!!!!!!!!!!!!!");
    }

	
}

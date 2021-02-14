package com.demoweb.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class DemoWebListener implements ServletContextListener, HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  { 
         ServletContext application = se.getSession().getServletContext();
         int current = (int)application.getAttribute("current");
         application.setAttribute("current", current + 1); 	//현재 접속한 사용자 수
         int total = (int)application.getAttribute("total");
         application.setAttribute("total", total + 1);	//현재까지 접속한 총 누적 사용자 수
    }
    public void sessionDestroyed(HttpSessionEvent se)  {
    	 ServletContext application = se.getSession().getServletContext();
         int current = (int)application.getAttribute("current");
         application.setAttribute("current", current - 1);
    }
    public void contextDestroyed(ServletContextEvent sce)  { 
         // 누적 접속자 수를 파일에 저장하는 코드
    }
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	application.setAttribute("current", 0); 	//현재 접속한 사용자 수
        application.setAttribute("total", 0);	//현재까지 접속한 총 누적 사용자 수
    }
	
}

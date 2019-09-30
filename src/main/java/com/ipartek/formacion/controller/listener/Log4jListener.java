package com.ipartek.formacion.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import com.ipartek.formacion.controller.backoffice.UsuariosImportController;

/**
 * Application Lifecycle Listener implementation class LogListener
 *
 */
@WebListener
public class Log4jListener implements ServletContextListener {
	private final static Log LOG = LogFactory.getLog(Log4jListener.class);

    /**
     * Default constructor. 
     */
    public Log4jListener() {
    	BasicConfigurator.configure();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	LOG.info("Arranca la App y carga el contexto servlets");         
    }
	
}

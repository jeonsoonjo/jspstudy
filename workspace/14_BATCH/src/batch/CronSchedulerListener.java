package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CronSchedulerListener implements ServletContextListener {

    public CronSchedulerListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
       
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    }
	
}



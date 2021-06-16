package batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.SchedulerException;

@WebListener
public class CronSchedulerListener implements ServletContextListener {

	// field
	CronScheduler cronScheduler;
	
	// constructor
    public CronSchedulerListener() { }

    // method
    public void contextDestroyed(ServletContextEvent sce)  { 
       System.out.println("======================");
       try {
    	   cronScheduler.scheduler.clear();
    	   cronScheduler.scheduler.shutdown();
       } catch (SchedulerException e) {
    	   e.printStackTrace();
       }
    }

    public void contextInitialized(ServletContextEvent sce)  { 
        System.out.println("==== 최대 조회수 게시글 ====");
        cronScheduler = new CronScheduler("0 0/1 * 1/1 * ? *", TopHitJob.class);
        cronScheduler.execute();
    }
	
}



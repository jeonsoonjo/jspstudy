package ex_listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@WebListener // 나는 리스너이다
public class MyListener implements ServletContextListener {

	// 스케쥴러 생성(try-catch문에서 생성!)
	SchedulerFactory factory = null;
	Scheduler scheduler = null;
	
    public MyListener() {
    	try {
    		factory = new StdSchedulerFactory();
    		scheduler = factory.getScheduler();
    	} catch (SchedulerException e) {
			e.printStackTrace();
		}
    		
   	}

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    	// 웹 서비스가 종료되었을 때(톰켓에서 프로젝트가 제거될 때)
    	System.out.println("====== WebService Stop! ======");

    	try {
    		
    		// 스케쥴러 종료
    		scheduler.shutdown(); 
    		
    	} catch (SchedulerException e) {
			e.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent arg0)  {
    	
    	// 웹 서비스가 시작되었을 때(톰캣에 프로젝트 올라갈 때 )
    	System.out.println("====== WebService Start! ======");
    	

    	try {
    		
    		// Job(스케쥴러가 할 일)
    		JobDetail job = JobBuilder.newJob(MyJob.class)
    				.withIdentity("myJob", "group2")
    				.build();
    		
    		// Trigger(일하는 시간)
    		// CronTrigger 사용(가장 많이 사용)
    		CronTrigger trigger = TriggerBuilder.newTrigger()
    				.withIdentity("myTrigger", "group2")
    				.startNow()
    				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")) // cronSchedule()에 전달할 인수는 cron식이다(cronMaker에서 생성)
    				.build();
    		
        	// 스케쥴러 시작
    		scheduler.start();
    		
    		// 스케쥴러에 job, trigger 전달
    		scheduler.scheduleJob(job, trigger);
    		
    		
    		
    	} catch (SchedulerException e) {
			e.printStackTrace();
		} 
    	
    }
	
}



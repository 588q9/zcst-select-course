package top.ashonecoder.zcstselectcourse.controller.executor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import top.ashonecoder.zcstselectcourse.controller.Controller;

public class TestQuartzJob implements Job {

private static long startTime=System.currentTimeMillis();
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

long temp;
        System.out.println("start...");
        System.out.println(context.getJobDetail().getJobDataMap().get(Controller.SELECT_COURSE_DATA));
        System.out.println((temp=System.currentTimeMillis())-startTime);
        startTime=temp;
    }
}

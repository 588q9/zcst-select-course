package top.ashonecoder.zcstselectcourse.controller;

import org.apache.coyote.Request;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ashonecoder.zcstselectcourse.controller.executor.SelectCourseJob;
import top.ashonecoder.zcstselectcourse.controller.executor.TestQuartzJob;
import top.ashonecoder.zcstselectcourse.domain.UserSession;
import top.ashonecoder.zcstselectcourse.properties.SelectCourseProperties;
import top.ashonecoder.zcstselectcourse.properties.UserProperties;
import top.ashonecoder.zcstselectcourse.service.LoginService;
import top.ashonecoder.zcstselectcourse.service.SelectCourseService;
import top.ashonecoder.zcstselectcourse.service.Service;
import top.ashonecoder.zcstselectcourse.util.RequestMapUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MainController implements Controller{
    private Scheduler scheduler ;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    RequestMapUtil requestMapUtil;

    @Autowired
    SelectCourseProperties selectCourseProperties;
    @Autowired
    private LoginService loginService;
    @Value("${spring.profiles.active}")
    String env;
    private static long startTime=System.currentTimeMillis();
    private final Job testJob=(jobExecutionContext)->{
        long temp;
        System.out.println("start...");
        System.out.println((temp=System.currentTimeMillis())-startTime);
        startTime=temp;

    };



    public void start(){
        try {
            this.scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

    public MainController()  {




    }

    @PostConstruct
    private void init() throws SchedulerException {



        scheduler= StdSchedulerFactory.getDefaultScheduler();


        JobDetail job = JobBuilder.newJob(
                //测试job，生产环境使用SelectCourseJob
               !env.equals("prod")?TestQuartzJob.class: SelectCourseJob.class
        ).build();

        if (env.equals("prod")){

//生产环境
        UserSession userSession=new UserSession();
        loginService.login(userSession);
        job.getJobDataMap().put(Controller.SELECT_COURSE_DATA,requestMapUtil.getMyCourseList());
job.getJobDataMap().put(Service.SELECT_COURSE_SERVICE,selectCourseService);
job.getJobDataMap().put(USER_SESSION,userSession);
}

       Trigger trigger= TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.cronSchedule(selectCourseProperties.getCronExpression())).build();
scheduler.scheduleJob(job,trigger);
if (env.equals("prod")){
    this.start();
}


    }
@PreDestroy
private void shutdown() throws SchedulerException {
        this.scheduler.shutdown();

}


}

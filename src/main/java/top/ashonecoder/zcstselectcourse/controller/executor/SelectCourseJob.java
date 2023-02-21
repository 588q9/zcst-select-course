package top.ashonecoder.zcstselectcourse.controller.executor;

import lombok.Data;
import org.apache.catalina.User;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import top.ashonecoder.zcstselectcourse.controller.Controller;
import top.ashonecoder.zcstselectcourse.domain.UserSession;
import top.ashonecoder.zcstselectcourse.service.SelectCourseService;
import top.ashonecoder.zcstselectcourse.service.Service;

import java.util.List;
import java.util.Map;

@Data
public class SelectCourseJob implements Job {



    @Override
    public void execute(JobExecutionContext context) {
        List<Map<String,String>> selectCourseData= (List<Map<String, String>>) context.getJobDetail().getJobDataMap().get(Controller.SELECT_COURSE_DATA);
        SelectCourseService selectCourseService= (SelectCourseService) context.getJobDetail().getJobDataMap().get(Service.SELECT_COURSE_SERVICE);
        UserSession userSession= (UserSession) context.getJobDetail().getJobDataMap().get(Controller.USER_SESSION);

        if (selectCourseData==null){
    throw new  NullPointerException("选课数据不能为空");
}
        for (Map<String,String> item:selectCourseData) {
            System.out.println("正在选的课: "+item.get("kcmc"));
            selectCourseService.selectCourse(userSession,item);

        }

    }
}

package top.ashonecoder.zcstselectcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ashonecoder.zcstselectcourse.properties.SelectCourseProperties;
import top.ashonecoder.zcstselectcourse.properties.UserProperties;

import java.util.Date;

@Component
public class UrlUtil {
    @Autowired
    SelectCourseProperties selectCourseProperties;
    @Autowired
    UserProperties userProperties;
    public  String getLoginUrl(){
        String loginUrl=selectCourseProperties.getLoginInterface();

return  loginUrl+"?time="+new Date().getTime();

    }
public String getLoginPageUrl(){
     return    selectCourseProperties.getLoginPageInterface();
}

public String getMenuUrl(){
        return  selectCourseProperties.getLoginMenuInterface()+"?jsdm=&_t="+new Date();
}
public  String getInfoFirstUrl(){
        return selectCourseProperties.getInfoInterfaces().get(0)+userProperties.getStudentNumber();
}
public String getSelectCourseUrl(){
        return selectCourseProperties.getSelectCourseInterface();
}
public String getCourseOptionalListUrl(){
        return  selectCourseProperties.getCourseoOptionalListInterface();
}

public String getGnmkdm(){
        return selectCourseProperties.getOtherData()[1]+"="+selectCourseProperties.getOtherData()[0];
}

}

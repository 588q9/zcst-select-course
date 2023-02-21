package top.ashonecoder.zcstselectcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import top.ashonecoder.zcstselectcourse.properties.UserProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RequestMapUtil {

    @Autowired
    UserProperties userProperties;

public List<Map<String, String>> selectCourseMap(){

return userProperties.getCourseList();
}

public HttpHeaders getURLEncodeHeaders(){
    HttpHeaders headers=new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  return headers;
}

    public MultiValueMap<String,String> userLoginMap(){
        MultiValueMap<String, String> content= new LinkedMultiValueMap<>();
        content.set("language","zh_CN");
        content.set("yhm",userProperties.getStudentNumber());

        content.set("mm",userProperties.getPassword());

        return content;
    }
public HttpEntity<MultiValueMap<String,String>> buildRequest(MultiValueMap<String,String> content,HttpHeaders headers){


    return new HttpEntity<>(content,headers);


}

public List<Map<String,String>> getMyCourseList(){
    List<Map<String,String>> courseList=userProperties.getCourseList();
    List<Map<String,String>> processCourseList=new ArrayList<>();
    for (Map<String,String> item:courseList){
        Map<String,String> buildCourse=this.getSelectCourseBaseMap();
        processCourseList.add(buildCourse);
        buildCourse.putAll(item);


    }

    return processCourseList;
}



private Map<String,String> getSelectCourseBaseMap(){
    Map<String,String> baseContent=new HashMap<>();
    baseContent.put("rwlx","1");

    baseContent.put("rlkz","0");
    baseContent.put("rlzlkz","1");

    baseContent.put("sxbj","1");

    baseContent.put("xxkbj","0");
    baseContent.put("ckbj","0");
    baseContent.put("kklxdm","01");
    baseContent.put("njdm_id","2020");

    baseContent.put("zyh_id","0402");

    baseContent.put("xklc","1");

    baseContent.put("xkxnm","2022");
    baseContent.put("xkxqm","3");
    return baseContent;
}




}

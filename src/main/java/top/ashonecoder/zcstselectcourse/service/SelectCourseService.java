package top.ashonecoder.zcstselectcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import top.ashonecoder.zcstselectcourse.domain.UserSession;
import top.ashonecoder.zcstselectcourse.properties.UserProperties;
import top.ashonecoder.zcstselectcourse.util.RequestMapUtil;
import top.ashonecoder.zcstselectcourse.util.UrlUtil;

import java.util.HashMap;
import java.util.Map;

@Component
public class SelectCourseService implements Service{
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RestTemplate asyncRestTemplate;

    @Autowired
    UrlUtil urlUtil;

@Autowired
UserProperties userProperties;
@Autowired
RequestMapUtil requestMapUtil;


public String getSelectCourseOptionalList(UserSession session){
HttpHeaders headers=requestMapUtil.getURLEncodeHeaders();
    Map<String, String> content=new HashMap<>();
    content.put("su",userProperties.getStudentNumber());

content.put("layout","default");
this.buildCookieToHeaders(session,headers);

    HttpEntity<MultiValueMap<String, String>> request=new HttpEntity<>(null,headers);

return    restTemplate.exchange(urlUtil.getCourseOptionalListUrl()+"?su="+content.get("su")+"&layout="+content.get("layout")+"&"+urlUtil.getGnmkdm(),
        HttpMethod.GET,request,String.class).getBody();




}



public String selectCourse(UserSession userSession,Map<String,String> courseInfo){
    HttpHeaders headers=requestMapUtil.getURLEncodeHeaders();


    this.buildCookieToHeaders(userSession,headers);

    HttpEntity<Map<String,String>> request=new HttpEntity<>(courseInfo,headers);


    return asyncRestTemplate.postForEntity(urlUtil.getSelectCourseUrl(),request,String.class).getBody();


}






}

package top.ashonecoder.zcstselectcourse.service;

import org.springframework.http.HttpHeaders;
import top.ashonecoder.zcstselectcourse.domain.UserSession;
import top.ashonecoder.zcstselectcourse.util.CookieKeyUtil;

import java.util.Iterator;
import java.util.Map;

public interface Service {

    String SELECT_COURSE_SERVICE="selectCourseService";
    default void buildCookieToSession(UserSession userSession, HttpHeaders headers){

        for (String item :headers.get(CookieKeyUtil.SetCookie.getKey())) {
            String[] temps=item.split("[=;]");
            userSession.getCookies().put(temps[0],temps[0]+"="+temps[1]);
        }


    }

    default void buildCookieToHeaders(UserSession userSession, HttpHeaders headers){

        Map<String,String> cookies=userSession.getCookies();
        Iterator<Map.Entry<String,String>> iterator=cookies.entrySet().iterator();
        StringBuilder temp= new StringBuilder(iterator.next().getValue());

while (iterator.hasNext()){
    temp.append("; ").append(iterator.next().getValue());
}

headers.set(CookieKeyUtil.Cookie.getKey(), temp.toString());


    }


}

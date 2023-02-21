package top.ashonecoder.zcstselectcourse;

import org.junit.jupiter.api.Test;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import top.ashonecoder.zcstselectcourse.controller.Controller;
import top.ashonecoder.zcstselectcourse.controller.MainController;
import top.ashonecoder.zcstselectcourse.domain.UserSession;
import top.ashonecoder.zcstselectcourse.properties.SelectCourseProperties;
import top.ashonecoder.zcstselectcourse.service.LoginService;
import top.ashonecoder.zcstselectcourse.service.SelectCourseService;
import top.ashonecoder.zcstselectcourse.util.CookieKeyUtil;
import top.ashonecoder.zcstselectcourse.util.RequestMapUtil;
import top.ashonecoder.zcstselectcourse.util.UrlUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CyclicBarrier;

@SpringBootTest
class ZcstSelectCourseApplicationTests {
@Autowired
LoginService loginService;


@Autowired
MainController mainController;
@Autowired
UrlUtil urlUtil;
@Autowired
RequestMapUtil requestMapUtil;
@Autowired
RestTemplate restTemplate;
@Autowired
SelectCourseService selectCourseService;

    @Test
    void getLoginInfoTest() {
                UserSession session=new UserSession();
        loginService.login(session);
//        UserSession session=new UserSession();
        loginService.login(session);
        HttpHeaders afterLoginHeaders=new HttpHeaders();
        afterLoginHeaders.set(CookieKeyUtil.Cookie.getKey(),session.getCookies().get(CookieKeyUtil.JSESSIONID.getKey()
        )+"; "+session.getCookies().get(CookieKeyUtil.route.getKey()));

//get设置header
        HttpEntity<MultiValueMap<String, String>> afterRequest = new HttpEntity<>(null, afterLoginHeaders);
         restTemplate.exchange(urlUtil.getInfoFirstUrl(), HttpMethod.GET
                ,afterRequest,
                String.class
        );



    }


    @Test
    public void  getCourseTest(){
        UserSession session=new UserSession();
        loginService.login(session);

        String message= selectCourseService.getSelectCourseOptionalList(session);
        System.out.println(message);
    }
@Test
    public  void buildCourseMapTest(){

        requestMapUtil.getMyCourseList();

    }

    @Autowired
    AsyncRestTemplate asyncRestTemplate;

@Test
public void nettyHttpConnectionTest(){
    ListenableFutureCallback<ResponseEntity<String>> callback=new ListenableFutureCallback<>() {
        @Override
        public void onFailure(Throwable ex) {

        }

        @Override
        public void onSuccess(ResponseEntity<String> result) {
            System.out.println(result.getBody());
        }
    };
    System.out.println(1);

    asyncRestTemplate.getForEntity("https://jw.zcst.edu.cn/xtgl/login_slogin.html",String.class).addCallback(callback);

    System.out.println(2);
    asyncRestTemplate.getForEntity("",String.class).addCallback(callback);

    try {
        Thread.currentThread().join();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }


}




@Test
public void QuartzTest(){




    mainController.start();

    try {
        Thread.currentThread().join();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
@Value("${spring.profiles.active}")
String env;
@Test
public  void envTest(){


    System.out.println(env);


}
}

package top.ashonecoder.zcstselectcourse.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import top.ashonecoder.zcstselectcourse.domain.UserSession;
import top.ashonecoder.zcstselectcourse.util.CookieKeyUtil;
import top.ashonecoder.zcstselectcourse.util.RequestMapUtil;
import top.ashonecoder.zcstselectcourse.util.UrlUtil;
@Component
public class LoginService implements Service{

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RequestMapUtil requestMapUtil;

    @Autowired
    UrlUtil urlUtil;



    private void getLoginCsrfToken(UserSession userSession){
        ResponseEntity<String> response=restTemplate.getForEntity(urlUtil.getLoginPageUrl(),String.class);

        buildCookieToSession(userSession,response.getHeaders());

        Document document = Jsoup.parse(response.getBody());


        userSession.setCsrfToken(document.select("#csrftoken").get(0).val());

    }

    public void login(UserSession session){


        this.getLoginCsrfToken(session);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(CookieKeyUtil.Cookie.getKey(),session.getCookies().get(CookieKeyUtil.JSESSIONID.getKey())+"; "+session.
                getCookies().get(CookieKeyUtil.route.getKey()));

        MultiValueMap<String,String> content=requestMapUtil.userLoginMap();
        content.set("csrftoken",session.getCsrfToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(content, headers);



        ResponseEntity<String> response=restTemplate.postForEntity( urlUtil.getLoginUrl(),request  ,
                String.class );
        buildCookieToSession(session,response.getHeaders());
        session.getCookies().remove("rememberMe");
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

}

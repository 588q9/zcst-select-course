package top.ashonecoder.zcstselectcourse.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class UserSession {


    private String csrfToken;
private  Map<String,String> cookies=new ConcurrentHashMap<>();

}

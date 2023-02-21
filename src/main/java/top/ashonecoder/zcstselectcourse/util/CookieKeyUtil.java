package top.ashonecoder.zcstselectcourse.util;

import lombok.Getter;

@Getter
public enum CookieKeyUtil {

    JSESSIONID("JSESSIONID"),
    route("route"),
    SetCookie("Set-Cookie"),
    Cookie("Cookie")
    ;

private final String key;
    CookieKeyUtil(String key) {
        this.key=key;

    }
}

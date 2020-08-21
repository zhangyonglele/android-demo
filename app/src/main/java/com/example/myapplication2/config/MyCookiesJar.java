package com.example.myapplication2.config;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyCookiesJar implements CookieJar {
    private static Map<HttpUrl, List<Cookie>> cookiesss = new ConcurrentHashMap<>();


    /**
     * Saves {@code cookies} from an HTTP response to this store according to this jar's policy.
     *
     * <p>Note that this method may be called a second time for a single HTTP response if the response
     * includes a trailer. For this obscure HTTP feature, {@code cookies} contains only the trailer's
     * cookies.
     *
     * @param url
     * @param cookies
     */
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookiesss.put(url,cookies);
        System.out.println("test");
    }

    /**
     * Load cookies from the jar for an HTTP request to {@code url}. This method returns a possibly
     * empty list of cookies for the network request.
     *
     * <p>Simple implementations will return the accepted cookies that have not yet expired and that
     * {@linkplain Cookie#matches match} {@code url}.
     *
     * @param url
     */
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> thisCookies = cookiesss.get(url);
//        if(thisCookies == null) {
//            return new ArrayList<>();
//        }else {
//            // 无效cookie
//            List<Cookie> invalidCookies = new ArrayList<>();
//            //有效的Cookie
//            List<Cookie> validCookies = new ArrayList<>();
//            for (Cookie cookie : thisCookies) {
//                if (cookie.expiresAt() < System.currentTimeMillis()) {
//                    //判断是否过期
//                    invalidCookies.add(cookie);
//                } else if (cookie.matches(url)) {
//                    //匹配Cookie对应url
//                    validCookies.add(cookie);
//                }
//            }
//            //缓存中移除过期的Cookie
//            //thisCookies.removeAll(invalidCookies);
//            //返回List<Cookie>让Request进行设置
//            return validCookies;
//        }
        return thisCookies == null ? new ArrayList<Cookie>() : thisCookies;
    }
}

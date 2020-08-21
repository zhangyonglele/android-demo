package com.example.myapplication2.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HeaderInterceptor {
    private static String session;
    public static Interceptor setHeader(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
//                if(originalRequest.body() == null) {
//                    return chain.proceed(originalRequest);
//                }
                if(session != null) {
                    Request newRequest = originalRequest.newBuilder()
                        .addHeader("Cookie",session)
                        .build();
                    return chain.proceed(newRequest);
                }
                Response originalResp = chain.proceed(chain.request());
                if(session == null) {
                    session = originalResp.header("Set-Cookie");
                    session = session.substring(0,session.indexOf(";"));
                }
                return chain.proceed(originalRequest);
            }
        };
    }

    public static Interceptor getHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                return null;
            }
        };
    }
}

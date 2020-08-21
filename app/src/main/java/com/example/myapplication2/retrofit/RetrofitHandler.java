package com.example.myapplication2.retrofit;

import android.util.Log;
import com.example.myapplication2.config.HeaderInterceptor;
import com.example.myapplication2.config.MyCookiesJar;
import com.example.myapplication2.request.RequestApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitHandler {
    private static final String TAG = "RetrofitHandler";

    private static RetrofitHandler retrofitHandler;

    private OkHttpClient okHttpClient;

    private Retrofit retrofit;

    private RequestApi requestApi;

    public RetrofitHandler() {
        initRetrofit();
    }

    public RequestApi getRequestApi() {
        return requestApi;
    }

    public static RetrofitHandler getInstance() {
        if (retrofitHandler == null) {
            synchronized (RetrofitHandler.class) {
                if(retrofitHandler == null) {
                    retrofitHandler = new RetrofitHandler();
                }
            }
        }
        return retrofitHandler;
    }

    public void initOKHttpClint() {
        if (okHttpClient == null ) {
            synchronized (RetrofitHandler.class) {
                if(okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            //.addInterceptor(HeaderInterceptor.getHeader())
                            .addInterceptor(HeaderInterceptor.setHeader())
                            //.cookieJar(new MyCookiesJar())
                            .build();
                }
            }
        }
    }

    public void initRetrofit() {
        initOKHttpClint();
        Log.i(TAG,"init retrofit");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://118.178.190.4/secondproject/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        requestApi = retrofit.create(RequestApi.class);
        Log.i(TAG,"retrofit init done");
    }
}

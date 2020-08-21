package com.example.myapplication2.request;

import com.example.myapplication2.model.Project;
import com.example.myapplication2.util.response.UniversalResponseBody;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface RequestApi {
    @POST("user/login")
    @FormUrlEncoded
    Observable<UniversalResponseBody<String>> login(@Field("account") String userName, @Field("password") String password);

    @GET("group/join/me")
    Observable<UniversalResponseBody<List<Project>>> getMyProject();
}

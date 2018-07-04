package com.example.zcc.myapplication.rxsample.http;


import com.example.zcc.myapplication.rxsample.enity.HttpResult;
import com.example.zcc.myapplication.rxsample.enity.NewsEntity;
import com.example.zcc.myapplication.rxsample.enity.Subject;
import com.example.zcc.myapplication.rxsample.enity.UserEntity;
import com.example.zcc.myapplication.ui.entity.MeiTuBean;
import com.example.zcc.myapplication.ui.entity.VideoEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 *
 */

public interface ApiService {
    @GET("/student/mobileRegister")
    Observable<HttpResult<UserEntity>> login(@Query("phone") String phone, @Query("password") String psw);
    @GET("satinApi")
    Observable<HttpResult<List<NewsEntity>>> getNews(@Query("type") String type, @Query("page") int  page);
    @GET("meituApi")
    Observable<HttpResult<List<MeiTuBean>>> getMeiTu(@Query("page") int  page);
    @GET("satinGodApi")
    Observable<HttpResult<List<VideoEntity>>> getStory(@Query("page") int  page);


    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResult<Subject>> getUser(@Query("touken") String touken);

}

package com.oeasy9999.zhihudemo.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oeasy9999 on 2016/9/4.
 */
public class ApiService {
  public static Retrofit createApiService() {
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl(API.BASE)
        .build();
  }
  public static Retrofit createZhuanlanApiService() {
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl(API.BASE_ZHUANLAN)
        .build();
  }
}

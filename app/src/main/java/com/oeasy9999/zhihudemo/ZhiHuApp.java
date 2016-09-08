package com.oeasy9999.zhihudemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by oeasy9999 on 2016/8/19.
 */
public class ZhiHuApp extends Application {

  private static volatile Context appContext;

  @Override public void onCreate() {
    super.onCreate();
    //new Retrofit.Builder()
    //    .addConverterFactory(GsonConverterFactory.create())
    //    .baseUrl(API.BASE)
    //    .build();
    appContext = getApplicationContext();
  }

  public static Context appContext(){
    return appContext;
  }
}

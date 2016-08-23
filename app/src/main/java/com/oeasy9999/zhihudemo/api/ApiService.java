package com.oeasy9999.zhihudemo.api;

import com.oeasy9999.zhihudemo.api.service.SplashService;
import com.smartydroid.android.starter.kit.retrofit.RetrofitBuilder;
import retrofit2.Retrofit;

/**
 * Created by oeasy9999 on 2016/8/22.
 */
public class ApiService {

  private static Retrofit retrofit() {
    return RetrofitBuilder.get().retrofit();
  }

  //1.启动界面图像获取
  public static SplashService createSplashService() {
    return retrofit().create(SplashService.class);
  }
}

package com.oeasy9999.zhihudemo.api.service;

import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by oeasy9999 on 2016/8/22.
 */
public interface SplashService {
  @GET("4/start-image/1080*1776") Call<SplashImage> getSplashImage();
}

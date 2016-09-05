package com.oeasy9999.zhihudemo.service;

import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public interface SplashService {
  @GET("4/start-image/1080*1920") Observable<SplashImage> getSplashImage();
}

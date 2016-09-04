package com.oeasy9999.zhihudemo.service;

import com.oeasy9999.zhihudemo.model.entity.Remen;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by oeasy9999 on 2016/9/4.
 */
public interface RemenService {
  @GET("3/news/hot") Observable<Remen> getRemen();
}

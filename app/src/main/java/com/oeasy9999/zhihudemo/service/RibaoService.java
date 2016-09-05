package com.oeasy9999.zhihudemo.service;

import com.oeasy9999.zhihudemo.model.entity.Ribao;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by oeasy9999 on 2016/9/4.
 */
public interface RibaoService {
  @GET("4/news/latest") Observable<Ribao> getRibaoLatest();
  @GET("4/news/before/{data}") Observable<Ribao> getRibaoBefore(@Path("data") String date);
}

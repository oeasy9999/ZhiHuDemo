package com.oeasy9999.zhihudemo.service;

import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public interface NewsDetailService {
  @GET("4/news/{id}") Observable<NewsDetail> getNewsDetail(@Path("id") int id);
}

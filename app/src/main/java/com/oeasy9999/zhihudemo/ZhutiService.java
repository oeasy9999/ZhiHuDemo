package com.oeasy9999.zhihudemo;

import com.oeasy9999.zhihudemo.model.entity.ZhutiList;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public interface ZhutiService {
  @GET("4/themes") Observable<ZhutiList> getZhutiList();
}

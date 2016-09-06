package com.oeasy9999.zhihudemo.service;

import com.oeasy9999.zhihudemo.model.entity.Theme;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public interface ThemeService {
  @GET("4/theme/{id}") Observable<Theme> getTheme(@Path("id") int id);
}

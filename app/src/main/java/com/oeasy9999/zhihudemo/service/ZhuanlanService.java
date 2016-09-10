package com.oeasy9999.zhihudemo.service;

import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by oeasy9999 on 2016/9/7.
 */
public interface ZhuanlanService {
  @GET("{slug}/posts") Observable<List<ZhuanlanPost>> getZhuanlanPost(
      @Path("slug") String slug,
      @Query("limit") int num,
      @Query("offset") int from
  );
  //@GET("posts/{slug}") Observable<ZhuanlanPost> getZhuanlanDetail(
  //    @Path("slug") int slug
  //);
}

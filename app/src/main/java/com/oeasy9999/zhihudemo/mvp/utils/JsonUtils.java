package com.oeasy9999.zhihudemo.mvp.utils;

import com.google.gson.Gson;
import com.oeasy9999.zhihudemo.model.entity.HotNews;
import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import com.oeasy9999.zhihudemo.model.entity.Remen;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.model.entity.StoryExtra;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class JsonUtils {

  public static Gson mGson = new Gson();

  public static Ribao parseRibao(String str){
    return mGson.fromJson(str, Ribao.class);
  }

  public static SplashImage parseSplash(String str) {
    return mGson.fromJson(str, SplashImage.class);
  }

  public static NewsDetail parseNewsDetail(String str) {
    return mGson.fromJson(str, NewsDetail.class);
  }

  public static Remen parseRemen(String str) {
    return mGson.fromJson(str, Remen.class);
  }

  public static HotNews parseHotNews(String str) {
    return mGson.fromJson(str, HotNews.class);
  }

  public static StoryExtra parseStoryExtra(String str) {
    return mGson.fromJson(str, StoryExtra.class);
  }
}

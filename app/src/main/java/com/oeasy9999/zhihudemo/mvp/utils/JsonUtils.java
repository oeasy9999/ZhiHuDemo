package com.oeasy9999.zhihudemo.mvp.utils;

import com.google.gson.Gson;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.model.entity.SplashImage;

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
}

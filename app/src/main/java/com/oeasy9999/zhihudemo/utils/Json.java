package com.oeasy9999.zhihudemo.utils;

import com.google.gson.Gson;
import com.oeasy9999.zhihudemo.model.entity.Zhuanlan;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;

/**
 * Created by oeasy9999 on 2016/9/7.
 */
public class Json {
  public static Gson mGson = new Gson();
  public static Zhuanlan parseZhuanlan(String string) {
    return mGson.fromJson(string, Zhuanlan.class);
  }
  public static ZhuanlanPost parseZhuanlanDetail(String string) {
    return mGson.fromJson(string, ZhuanlanPost.class);
  }
}

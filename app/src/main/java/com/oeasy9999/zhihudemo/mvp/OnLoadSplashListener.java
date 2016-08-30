package com.oeasy9999.zhihudemo.mvp;

import com.oeasy9999.zhihudemo.model.entity.SplashImage;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public interface OnLoadSplashListener {
  void onSuccess(SplashImage splashImage);
  void onFailure(String msg, Exception e);
}

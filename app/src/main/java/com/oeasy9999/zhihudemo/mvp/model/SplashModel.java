package com.oeasy9999.zhihudemo.mvp.model;

import android.content.Context;
import android.view.View;
import com.oeasy9999.zhihudemo.mvp.OnLoadSplashListener;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public interface SplashModel  {
  void getSplashImage(OnLoadSplashListener listener);
  void showAnimator(Context context, View view);
}

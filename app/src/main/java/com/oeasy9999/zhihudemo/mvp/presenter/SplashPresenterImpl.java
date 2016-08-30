package com.oeasy9999.zhihudemo.mvp.presenter;

import android.content.Context;
import android.view.View;
import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.mvp.OnLoadSplashListener;
import com.oeasy9999.zhihudemo.mvp.model.SplashModel;
import com.oeasy9999.zhihudemo.mvp.model.SplashModelImple;
import com.oeasy9999.zhihudemo.mvp.view.SplashView;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class SplashPresenterImpl implements SplashPresenter, OnLoadSplashListener{

  private SplashView mSplashView;
  private SplashModel mSplashModel;
  private Context mContext;

  public SplashPresenterImpl(SplashView splashView, Context context) {
    this.mSplashView = splashView;
    this.mSplashModel = new SplashModelImple(context);
    this.mContext = context;
  }

  @Override public void loadSplashImage() {
    //mSplashView.showSplashImage();
    mSplashModel.getSplashImage(this);
  }

  @Override public void animator(View view) {
    mSplashModel.showAnimator(mContext, view);
  }

  @Override public void onSuccess(SplashImage splashImage) {
    mSplashView.showSplashImage(splashImage);
  }

  @Override public void onFailure(String msg, Exception e) {
  }
}

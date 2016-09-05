package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.SplashModel;
import com.oeasy9999.zhihudemo.mvp.model.SplashModelImple;
import com.oeasy9999.zhihudemo.mvp.view.SplashView;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class SplashPresenterImpl implements SplashPresenter, OnLoadListener {

  private SplashView mSplashView;
  private SplashModel mSplashModel;
  //private Context mContext;
  private SplashImage splashImage;

  public SplashPresenterImpl(SplashView splashView) {
    this.mSplashView = splashView;
    this.mSplashModel = new SplashModelImple(this);
    //this.mContext = context;
  }

  public void setSplashImage(SplashImage splashImage) {
    this.splashImage = splashImage;
  }

  @Override public void loadSplashImage() {
    //mSplashView.showSplashImage();
    mSplashModel.getSplashImage(this);
  }

  //@Override public void animator(View view) {
  //  mSplashModel.showAnimator(mContext, view);
  //}

  @Override public void onSuccess() {
    mSplashView.showSplashImage(splashImage);
  }

  @Override public void onFailure(String msg, Exception e) {
  }
}

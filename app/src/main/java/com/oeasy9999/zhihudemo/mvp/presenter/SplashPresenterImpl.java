package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.IModel;
import com.oeasy9999.zhihudemo.mvp.model.SplashModelImple;
import com.oeasy9999.zhihudemo.mvp.view.SplashView;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class SplashPresenterImpl implements IPresenter, OnLoadListener {

  private SplashView mSplashView;
  private IModel mSplashModel;
  private SplashImage splashImage;

  public SplashPresenterImpl(SplashView splashView) {
    this.mSplashView = splashView;
    this.mSplashModel = new SplashModelImple(this);
  }

  public void setSplashImage(SplashImage splashImage) {
    this.splashImage = splashImage;
  }

  @Override public void loadData() {
    mSplashModel.getData(this);
  }

  @Override public void onSuccess() {
    mSplashView.showSplashImage(splashImage);
  }

  @Override public void onFailure(String msg, Exception e) {
  }
}

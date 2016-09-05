package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.SplashPresenterImpl;
import com.oeasy9999.zhihudemo.service.ApiService;
import com.oeasy9999.zhihudemo.service.SplashService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class SplashModelImple implements IModel {

  public static final String TAG = "SplashModelImple";
  private SplashPresenterImpl splashPresenter;

  public SplashModelImple(SplashPresenterImpl splashPresenter) {
    this.splashPresenter = splashPresenter;
  }

  @Override public void getData(final OnLoadListener listener) {
    SplashService splashService = ApiService.createApiService().create(SplashService.class);
    splashService.getSplashImage()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<SplashImage>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("load splashimage failed!", (Exception) e);
          }

          @Override public void onNext(SplashImage splashImage) {
            splashPresenter.setSplashImage(splashImage);
          }
        });
  }
}

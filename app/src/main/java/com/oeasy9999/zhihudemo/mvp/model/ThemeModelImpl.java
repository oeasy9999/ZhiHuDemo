package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.model.entity.Theme;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.ThemePresenterImpl;
import com.oeasy9999.zhihudemo.service.ApiService;
import com.oeasy9999.zhihudemo.service.ThemeService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public class ThemeModelImpl implements NewsDetailModel {

  private ThemePresenterImpl themePresenter;

  public ThemeModelImpl(ThemePresenterImpl themePresenter) {
    this.themePresenter = themePresenter;
  }

  @Override public void getNewsDetail(int id, final OnLoadListener listener) {
    ThemeService themeService = ApiService.createApiService().create(ThemeService.class);
    themeService.getTheme(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Theme>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("loading theme failed!", (Exception) e);
          }

          @Override public void onNext(Theme theme) {
            themePresenter.setTheme(theme);
          }
        });
  }
}

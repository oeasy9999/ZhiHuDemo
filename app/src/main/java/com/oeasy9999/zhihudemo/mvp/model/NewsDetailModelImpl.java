package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.NewsDetailPresenterImpl;
import com.oeasy9999.zhihudemo.service.ApiService;
import com.oeasy9999.zhihudemo.service.NewsDetailService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public class NewsDetailModelImpl implements NewsDetailModel {

  NewsDetailPresenterImpl newsDetailPresenter;

  public NewsDetailModelImpl(NewsDetailPresenterImpl newsDetailPresenter) {
    this.newsDetailPresenter = newsDetailPresenter;
  }

  @Override public void getNewsDetail(int id, final OnLoadListener listener) {
    NewsDetailService newsDetailService = ApiService.createApiService().create(NewsDetailService.class);
    newsDetailService.getNewsDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<NewsDetail>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("load newsdetail failed", (Exception) e);
          }

          @Override public void onNext(NewsDetail newsDetail) {
            newsDetailPresenter.setNewsDetail(newsDetail);
          }
        });
  }
}

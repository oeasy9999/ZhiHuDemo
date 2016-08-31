package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadNewsDetailListener;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModel;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModelImple;
import com.oeasy9999.zhihudemo.mvp.view.NewsDetailView;

/**
 * Created by oeasy9999 on 2016/8/31.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, OnLoadNewsDetailListener {

  private NewsDetailView mNewsDetailView;
  private RibaoModel mRibaoModel;

  public NewsDetailPresenterImpl(NewsDetailView newsDetailView) {
    this.mNewsDetailView = newsDetailView;
    this.mRibaoModel = new RibaoModelImple();
  }

  @Override public void loadNewsDetail(int id) {
    mNewsDetailView.showProgress();
    mRibaoModel.getRibaoDetail(id, this);
  }

  @Override public void onSuccess(NewsDetail newsDetail) {
    if (newsDetail != null) {
      mNewsDetailView.showDetail(newsDetail);
    }
    mNewsDetailView.hidProgress();
  }

  @Override public void onFailure(String msg, Exception e) {
    mNewsDetailView.hidProgress();
  }
}

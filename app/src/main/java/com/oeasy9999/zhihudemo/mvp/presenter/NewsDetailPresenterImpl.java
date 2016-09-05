package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.NewsDetailModel;
import com.oeasy9999.zhihudemo.mvp.model.NewsDetailModelImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;

/**
 * Created by oeasy9999 on 2016/8/31.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter, OnLoadListener {

  //private NewsDetailView mNewsDetailView;
  private IView<NewsDetail> newsDetailView;
  //private RibaoModel mRibaoModel;
  private NewsDetailModel newsDetailModel;
  private NewsDetail newsDetail;

  public NewsDetailPresenterImpl(IView<NewsDetail> newsDetailView) {
    this.newsDetailView = newsDetailView;
    //this.mRibaoModel = new RibaoModelImple();
    this.newsDetailModel = new NewsDetailModelImpl(this);
  }

  public void setNewsDetail(NewsDetail newsDetail) {
    this.newsDetail = newsDetail;
  }

  @Override public void loadNewsDetail(int id) {
    newsDetailView.showProgress();
    newsDetailModel.getNewsDetail(id, this);
    //mRibaoModel.getRibaoDetail(id, this);
  }

  @Override public void onSuccess() {
    newsDetailView.hideProgress();
    if (newsDetail != null) {
      newsDetailView.showData(newsDetail);
    } else {
      newsDetailView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    newsDetailView.hideProgress();
  }
}

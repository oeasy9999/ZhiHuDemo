package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.Zhuti;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.ZhutiListModelImpl;
import com.oeasy9999.zhihudemo.mvp.view.ZhutiView;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiPresenterImpl implements ZhutiPresenter, OnLoadListener {

  public static final String TAG = "ZhutiPresenterImpl";
  private List<Zhuti> zhutiList;
  private ZhutiView zhutiView;
  private ZhutiListModelImpl zhutiListModel;

  public ZhutiPresenterImpl(ZhutiView zhutiView) {
    this.zhutiView = zhutiView;
    zhutiListModel = new ZhutiListModelImpl(this);
  }

  public void setZhutiList(List<Zhuti> zhutiList) {
    this.zhutiList = zhutiList;
  }

  @Override public void loadZhutiList() {
    zhutiListModel.getNews(this);
  }

  @Override public void onSuccess() {
    zhutiView.hideProgress();
    if (zhutiList != null) {
      zhutiView.showZhutiList(zhutiList);
    } else {
      zhutiView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    zhutiView.hideProgress();
    zhutiView.showLoadFailMsg();
  }
}

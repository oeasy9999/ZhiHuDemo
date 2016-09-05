package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.ZhutiList;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.ZhutiListModelImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiPresenterImpl implements IPresenter, OnLoadListener {

  public static final String TAG = "ZhutiPresenterImpl";
  private ZhutiList zhutiList;
  private IView<ZhutiList> zhutiView;
  private ZhutiListModelImpl zhutiListModel;

  public ZhutiPresenterImpl(IView<ZhutiList> zhutiView) {
    this.zhutiView = zhutiView;
    zhutiListModel = new ZhutiListModelImpl(this);
  }

  public void setZhutiList(ZhutiList zhutiList) {
    this.zhutiList = zhutiList;
  }

  @Override public void loadData() {
    zhutiListModel.getData(this);
  }

  @Override public void onSuccess() {
    zhutiView.hideProgress();
    if (zhutiList != null) {
      zhutiView.showData(zhutiList);
    } else {
      zhutiView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    zhutiView.hideProgress();
    zhutiView.showLoadFailMsg();
  }
}

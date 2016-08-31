package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.API;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadNewsListener;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModel;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModelImple;
import com.oeasy9999.zhihudemo.mvp.view.RibaoView;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class RibaoPresenterImpl implements RibaoPresenter, OnLoadNewsListener {

  private RibaoView mRibaoView;
  private RibaoModel mRibaoModel;

  public RibaoPresenterImpl(RibaoView ribaoView) {
    this.mRibaoView = ribaoView;
    this.mRibaoModel = new RibaoModelImple();
  }

  @Override public void loadLaest() {
    mRibaoView.showProgress();
    mRibaoModel.getRibao(API.TYPE_LATEST, this);
  }

  @Override public void loadBefore() {
    mRibaoModel.getRibao(API.TYPE_BEFORE, this);
  }

  @Override public void onSuccess(Ribao ribao) {
    mRibaoView.hideProgress();
    mRibaoView.showRibao(ribao);
  }

  @Override public void onFailure(String msg, Exception e) {
    mRibaoView.hideProgress();
    mRibaoView.showLoadFailMsg();
  }
}

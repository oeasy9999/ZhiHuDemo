package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.API;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModel;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModelImple;
import com.oeasy9999.zhihudemo.mvp.view.RibaoView;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class RibaoPresenterImpl implements RibaoPresenter, OnLoadListener {

  private RibaoView mRibaoView;
  private RibaoModel mRibaoModel;
  private Ribao ribao;

  public RibaoPresenterImpl(RibaoView ribaoView) {
    this.mRibaoView = ribaoView;
    this.mRibaoModel = new RibaoModelImple(this);
  }

  public void setRibao(Ribao ribao) {
    this.ribao = ribao;
  }

  @Override public void loadLaest() {
    mRibaoView.showProgress();
    mRibaoModel.getRibao(API.TYPE_LATEST, this);
  }

  @Override public void loadBefore() {
    mRibaoModel.getRibao(API.TYPE_BEFORE, this);
  }

  @Override public void onSuccess() {
    mRibaoView.hideProgress();
    mRibaoView.showRibao(ribao);
  }

  @Override public void onFailure(String msg, Exception e) {
    mRibaoView.hideProgress();
    mRibaoView.showLoadFailMsg();
  }
}

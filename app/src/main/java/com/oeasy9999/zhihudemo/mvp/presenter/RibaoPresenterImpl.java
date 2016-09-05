package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModel;
import com.oeasy9999.zhihudemo.mvp.model.RibaoModelImple;
import com.oeasy9999.zhihudemo.mvp.view.IView;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class RibaoPresenterImpl implements RibaoPresenter, OnLoadListener {

  private IView<Ribao> mRibaoView;
  private RibaoModel mRibaoModel;
  private Ribao ribao;

  public RibaoPresenterImpl(IView<Ribao> ribaoView) {
    this.mRibaoView = ribaoView;
    this.mRibaoModel = new RibaoModelImple(this);
  }

  public void setRibao(Ribao ribao) {
    this.ribao = ribao;
  }

  @Override public void loadLaest(int type) {
    mRibaoModel.getRibao(type, this);
    mRibaoView.showProgress();
  }

  //@Override public void loadBefore() {
  //  mRibaoModel.getRibao(API.TYPE_BEFORE, this);
  //}

  @Override public void onSuccess() {
    mRibaoView.hideProgress();
    if (ribao != null) {
      mRibaoView.showData(ribao);
    } else {
      mRibaoView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    mRibaoView.hideProgress();
    mRibaoView.showLoadFailMsg();
  }
}

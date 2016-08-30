package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.mvp.view.MainView;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class MainPresenterImpl implements MainPresenter {

  private MainView mMainView;

  public MainPresenterImpl(MainView mainView) {
    this.mMainView = mainView;
  }

  @Override public void switchNavigation(int id) {
    switch (id) {
      case R.id.nav_ribao:
        mMainView.switchToRibao();
        break;
      case R.id.nav_remen:
        mMainView.switchToRemen();
        break;
      case R.id.nav_zhuti:
        mMainView.switchToZhuti();
        break;
      case R.id.nav_zhuanlan:
        mMainView.switchToZhuanlan();
        break;
      default:
        mMainView.switchToRibao();
        break;
    }
  }
}

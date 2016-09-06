package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.Theme;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.NewsDetailModel;
import com.oeasy9999.zhihudemo.mvp.model.ThemeModelImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public class ThemePresenterImpl implements NewsDetailPresenter, OnLoadListener {

  private IView<Theme> themeView;
  private NewsDetailModel themeModel;
  private Theme theme;

  public ThemePresenterImpl(IView<Theme> themeView) {
    this.themeView = themeView;
    this.themeModel = new ThemeModelImpl(this);
  }

  public void setTheme(Theme theme) {
    this.theme = theme;
  }

  @Override public void loadNewsDetail(int id) {
    themeView.showProgress();
    themeModel.getNewsDetail(id, this);
  }

  @Override public void onSuccess() {
    themeView.hideProgress();
    if (theme != null) {
      themeView.showData(theme);

    } else {
      themeView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    themeView.hideProgress();
  }
}

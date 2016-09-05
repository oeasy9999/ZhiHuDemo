package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.HotNews;
import com.oeasy9999.zhihudemo.model.entity.Remen;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.NewsModel;
import com.oeasy9999.zhihudemo.mvp.model.RemenModelImpl;
import com.oeasy9999.zhihudemo.mvp.view.RemenView;
import java.util.ArrayList;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class RemenPresenterImpl implements RemenPresenter, OnLoadListener {

  public static final String TAG = "RemenPresenterImpl";
  private RemenView remenView;
  private NewsModel remenModel;
  private ArrayList<HotNews> hotNewses;

  public RemenPresenterImpl(RemenView remenView) {
    this.remenView = remenView;
    this.remenModel = new RemenModelImpl(this);
  }

  public void setHotNewses(ArrayList<HotNews> hotNewses) {
    this.hotNewses = hotNewses;
  }

  @Override public void loadHotNews() {
    remenModel.getNews(this);
  }

  @Override public void onSuccess() {
    remenView.showRemen(hotNewses);
    remenView.hideProgress();
  }

  @Override public void onFailure(String msg, Exception e) {
    remenView.hideProgress();
    remenView.showLoadFailMsg();
  }
}

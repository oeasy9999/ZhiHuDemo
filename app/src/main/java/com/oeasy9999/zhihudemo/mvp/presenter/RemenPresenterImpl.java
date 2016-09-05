package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.Remen;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.IModel;
import com.oeasy9999.zhihudemo.mvp.model.RemenModelImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class RemenPresenterImpl implements IPresenter, OnLoadListener {

  public static final String TAG = "RemenPresenterImpl";
  private IView<Remen> remenView;
  private IModel remenModel;
  private Remen remen;

  public RemenPresenterImpl(IView<Remen> remenView) {
    this.remenView = remenView;
    this.remenModel = new RemenModelImpl(this);
  }

  public void setRemen(Remen remen) {
    this.remen = remen;
  }

  @Override public void loadData() {
    remenModel.getData(this);
  }

  @Override public void onSuccess() {
    remenView.hideProgress();
    if (remen != null) {
      remenView.showData(remen);
    } else {
      remenView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    remenView.hideProgress();
    remenView.showLoadFailMsg();
  }
}

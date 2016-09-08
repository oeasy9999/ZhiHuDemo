package com.oeasy9999.zhihudemo.mvp.presenter;

import android.util.Log;
import com.oeasy9999.zhihudemo.model.entity.Zhuanlan;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.ZhuanlanModelImpl;
import com.oeasy9999.zhihudemo.mvp.view.ZhuanlanView;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/7.
 */
public class ZhuanlanPresenterImpl implements OnLoadListener {

  private static final String TAG = "ZhuanlanPresenterImpl测试";
  private ZhuanlanView zhuanlanView;
  private ZhuanlanModelImpl zhuanlanModel;
  private List<Zhuanlan> zhuanlanList1;

  public ZhuanlanPresenterImpl(ZhuanlanView zhuanlanView) {
    this.zhuanlanView = zhuanlanView;
    zhuanlanModel = new ZhuanlanModelImpl(this);
  }

  public void setZhuanlanList1(List<Zhuanlan> zhuanlanList) {
    //Log.i(TAG, zhuanlanList.size() + "->");
    zhuanlanList1 = zhuanlanList;
    //Log.i(TAG, zhuanlanList1.size() + "-->");
  }

  public void loadData(String[] ids) {
    zhuanlanView.showProgress();
    zhuanlanModel.getZhuanlan(ids, this);
  }

  @Override public void onSuccess() {
    zhuanlanView.hideProgress();
    //if (zhuanlan == null) {
    //  Log.i(TAG, "zhuanlan为空");
    //} else {
    //  Log.i(TAG, zhuanlan.getName()+ "-->" + zhuanlan.getAvatarUrl());
    //}
    //if (zhuanlanList != null) {
    //Log.i(TAG, zhuanlanList1.size() + "--->");
    zhuanlanView.showData(zhuanlanList1);
    //} else {
    //  Log.i(TAG, "错误");
    //  zhuanlanView.showLoadFailMsg();
    //}
  }

  @Override public void onFailure(String msg, Exception e) {
    Log.i(TAG, msg);
    zhuanlanView.hideProgress();
    zhuanlanView.showLoadFailMsg();
  }
}

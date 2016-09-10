package com.oeasy9999.zhihudemo.mvp.presenter;

import android.util.Log;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.ZhuanlanDetailModel;
import com.oeasy9999.zhihudemo.mvp.view.IView;

/**
 * Created by oeasy9999 on 2016/9/9.
 */
public class ZhuanlanDetailPresenter implements NewsDetailPresenter, OnLoadListener {

  private static final String TAG = "DetailPresenter测试";
  private IView<ZhuanlanPost> zhuanlanPostIView;
  private ZhuanlanDetailModel zhuanlanDetailModel;
  private ZhuanlanPost zhuanlanPost;

  public ZhuanlanDetailPresenter(IView<ZhuanlanPost> zhuanlanPostIView) {
    this.zhuanlanPostIView = zhuanlanPostIView;
    this.zhuanlanDetailModel = new ZhuanlanDetailModel(this);
  }

  public void setZhuanlanPost(ZhuanlanPost zhuanlanPost) {
    this.zhuanlanPost = zhuanlanPost;
  }

  @Override public void loadNewsDetail(int id) {
    zhuanlanPostIView.showProgress();
    zhuanlanDetailModel.getNewsDetail(id, this);
  }

  @Override public void onSuccess() {
    zhuanlanPostIView.hideProgress();
    if (zhuanlanPost != null) {
      Log.i(TAG, zhuanlanPost.getPublishedTime());
      zhuanlanPostIView.showData(zhuanlanPost);
    } else {
      Log.i(TAG, "空");
      zhuanlanPostIView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    zhuanlanPostIView.showLoadFailMsg();
  }
}

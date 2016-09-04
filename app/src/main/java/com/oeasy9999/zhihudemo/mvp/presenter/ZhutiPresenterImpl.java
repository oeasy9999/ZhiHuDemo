package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.ZhutiService;
import com.oeasy9999.zhihudemo.model.entity.Zhuti;
import com.oeasy9999.zhihudemo.model.entity.ZhutiList;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.view.ZhutiView;
import com.oeasy9999.zhihudemo.service.ApiService;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiPresenterImpl implements ZhutiPresenter, OnLoadListener {

  public static final String TAG = "ZhutiPresenterImpl";
  private List<Zhuti> zhutiList;
  private ZhutiView zhutiView;

  public ZhutiPresenterImpl(ZhutiView zhutiView) {
    this.zhutiView = zhutiView;
  }

  @Override public void loadZhutiList() {
    ZhutiService zhutiService = ApiService.createApiService().create(ZhutiService.class);
    zhutiService.getZhutiList()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ZhutiList>() {
          @Override public void onCompleted() {
            onSuccess();
          }

          @Override public void onError(Throwable e) {
            onFailure("loading zhuti failed!", (Exception) e);
          }

          @Override public void onNext(ZhutiList zhutiList) {
            zhutiView.showZhutiList(zhutiList.getZhutis());
          }
        });
  }

  @Override public void onSuccess() {
    zhutiView.hideProgress();
  }

  @Override public void onFailure(String msg, Exception e) {
    zhutiView.hideProgress();
    zhutiView.showLoadFailMsg();
  }
}

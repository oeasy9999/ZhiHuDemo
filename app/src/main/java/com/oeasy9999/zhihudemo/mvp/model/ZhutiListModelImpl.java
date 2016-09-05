package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.service.ZhutiService;
import com.oeasy9999.zhihudemo.model.entity.ZhutiList;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhutiPresenterImpl;
import com.oeasy9999.zhihudemo.service.ApiService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/9/4.
 */
public class ZhutiListModelImpl implements IModel {

  private ZhutiPresenterImpl zhutiPresenter;

  public ZhutiListModelImpl(ZhutiPresenterImpl zhutiPresenter) {
    this.zhutiPresenter = zhutiPresenter;
  }

  @Override public void getData(final OnLoadListener listener) {
    ZhutiService zhutiService = ApiService.createApiService().create(ZhutiService.class);
    zhutiService.getZhutiList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<ZhutiList>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("loading zhuti failed!", (Exception) e);
          }

          @Override public void onNext(ZhutiList zhutiList) {
            //zhutiView.showZhutiList(zhutiList.getZhutis());
            zhutiPresenter.setZhutiList(zhutiList);
          }
        });
  }
}

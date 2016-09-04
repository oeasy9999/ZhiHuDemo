package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.model.entity.Remen;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.RemenPresenterImpl;
import com.oeasy9999.zhihudemo.service.ApiService;
import com.oeasy9999.zhihudemo.service.RemenService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/9/4.
 */
public class RemenModelImpl implements RemenModel {

  private RemenPresenterImpl remenPresenter;

  public RemenModelImpl(RemenPresenterImpl remenPresenter) {
    this.remenPresenter = remenPresenter;
  }

  @Override public void getRemen(final OnLoadListener listener) {
    RemenService remenService = ApiService.createApiService().create(RemenService.class);
    remenService.getRemen()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Remen>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("loading remen failed!", (Exception) e);
          }

          @Override public void onNext(Remen remen) {
            remenPresenter.setHotNewses(remen.getRecent());
          }
        });
  }
}

package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.model.entity.ZhuanlanComment;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanCommentPresenter;
import com.oeasy9999.zhihudemo.service.ZhuanlanService;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/9/12.
 */
public class ZhuanlanCommentModel {
  private ZhuanlanCommentPresenter zhuanlanCommentPresenter;

  public ZhuanlanCommentModel(ZhuanlanCommentPresenter zhuanlanCommentPresenter) {
    this.zhuanlanCommentPresenter = zhuanlanCommentPresenter;
  }

  public void getZhuanlanComments(int slug, int offset, final OnLoadListener listener) {
    ZhuanlanService zhuanlanService = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl("https://zhuanlan.zhihu.com/api/posts/")
        .build()
        .create(ZhuanlanService.class);
    zhuanlanService.getZhuanlanComment(slug, 20, offset)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<ZhuanlanComment>>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("load failed" , (Exception) e);
          }

          @Override public void onNext(List<ZhuanlanComment> zhuanlanComments) {
            zhuanlanCommentPresenter.setZhuanlanComments(zhuanlanComments);
          }
        });
  }
}

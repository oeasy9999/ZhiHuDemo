package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanPostsPresenter;
import com.oeasy9999.zhihudemo.service.ApiService;
import com.oeasy9999.zhihudemo.service.ZhuanlanService;
import java.util.List;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/9/8.
 */
public class ZhuanlanPostsModel {

  private ZhuanlanPostsPresenter zhuanlanPostsPresenter;

  public ZhuanlanPostsModel(ZhuanlanPostsPresenter zhuanlanPostsPresenter) {
    this.zhuanlanPostsPresenter = zhuanlanPostsPresenter;
  }

  public void getZhuanlanPosts(String slug, int offset, final OnLoadListener listener) {
    ZhuanlanService zhuanlanService = ApiService.createZhuanlanApiService().create(ZhuanlanService.class);
    zhuanlanService.getZhuanlanPost(slug, 10, offset)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<ZhuanlanPost>>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("load zhuanlanposts 失败", (Exception) e);
          }

          @Override public void onNext(List<ZhuanlanPost> zhuanlanPosts) {
            zhuanlanPostsPresenter.setZhuanlanPosts(zhuanlanPosts);
          }
        });
  }
}

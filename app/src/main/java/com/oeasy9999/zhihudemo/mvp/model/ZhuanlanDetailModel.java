package com.oeasy9999.zhihudemo.mvp.model;

import android.util.Log;
import com.oeasy9999.zhihudemo.service.API;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanDetailPresenter;
import com.oeasy9999.zhihudemo.utils.Json;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by oeasy9999 on 2016/9/9.
 */
public class ZhuanlanDetailModel implements NewsDetailModel {

  private static final String TAG = "DetailModel测试";
  private ZhuanlanDetailPresenter zhuanlanDetailPresenter;

  public ZhuanlanDetailModel(ZhuanlanDetailPresenter zhuanlanDetailPresenter) {
    this.zhuanlanDetailPresenter = zhuanlanDetailPresenter;
  }

  @Override public void getNewsDetail(int id, final OnLoadListener listener) {
    Log.i(TAG, id+"");
    Callback<ZhuanlanPost> zhuanlanPostCallback = new Callback<ZhuanlanPost>() {
      @Override public ZhuanlanPost parseNetworkResponse(Response response) throws Exception {
        return Json.parseZhuanlanDetail(response.body().string());
      }

      @Override public void onError(Call call, Exception e) {
        listener.onFailure("load failed" , (Exception) e);
      }

      @Override public void onResponse(ZhuanlanPost response) {
        Log.i(TAG, response.getContent());
        zhuanlanDetailPresenter.setZhuanlanPost(response);
        listener.onSuccess();
      }
    };
    OkHttpUtils.get().url(API.BASE_POST + id).build().execute(zhuanlanPostCallback);

    //ZhuanlanService zhuanlanService = ApiService.createZhuanlanApiService().create(ZhuanlanService.class);
    //zhuanlanService.getZhuanlanDetail(id)
    //    .subscribeOn(Schedulers.io())
    //    .observeOn(AndroidSchedulers.mainThread())
    //    .subscribe(new Observer<ZhuanlanPost>() {
    //      @Override public void onCompleted() {
    //        listener.onSuccess();
    //      }
    //
    //      @Override public void onError(Throwable e) {
    //        listener.onFailure("load Failed", (Exception) e);
    //      }
    //
    //      @Override public void onNext(ZhuanlanPost zhuanlanPost) {
    //        zhuanlanDetailPresenter.setZhuanlanPost(zhuanlanPost);
    //      }
    //    });
  }
}

package com.oeasy9999.zhihudemo.mvp.model;

import android.util.Log;
import com.oeasy9999.zhihudemo.service.API;
import com.oeasy9999.zhihudemo.model.entity.Zhuanlan;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanPresenterImpl;
import com.oeasy9999.zhihudemo.utils.Json;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by oeasy9999 on 2016/9/7.
 */
public class ZhuanlanModelImpl {

  private static final String TAG = "ZhuanlanModel测试";
  private ZhuanlanPresenterImpl zhuanlanPresenter;
  List<Zhuanlan> zhuanlanList = new ArrayList<Zhuanlan>();

  public ZhuanlanModelImpl(ZhuanlanPresenterImpl zhuanlanPresenter) {
    this.zhuanlanPresenter = zhuanlanPresenter;
  }

  public void getZhuanlan(final String[] ids, final OnLoadListener listener) {
    //ZhuanlanService zhuanlanService =
    //    ApiService.createZhuanlanApiService().create(ZhuanlanService.class);
    for (int i = 0; i < ids.length; i++) {
      Callback<Zhuanlan> callback = new Callback<Zhuanlan>() {
        @Override public Zhuanlan parseNetworkResponse(Response response) throws Exception {
          Zhuanlan zhuanlan = Json.parseZhuanlan(response.body().string());
          return zhuanlan;
        }

        @Override public void onError(Call call, Exception e) {
          listener.onFailure("错误", e);
        }

        @Override public void onResponse(Zhuanlan response) {
          if (zhuanlanList.size() < ids.length) {
            zhuanlanList.add(response);
            Log.i(TAG, response.getName()+"-->"+zhuanlanList.size());
          }
          if (zhuanlanList.size() == ids.length) {
            Log.i(TAG, ""+zhuanlanList.size());
            zhuanlanPresenter.setZhuanlanList1(zhuanlanList);
            //zhuanlanList.clear();
            listener.onSuccess();
          }
        }
      };
      //String url = " https://zhuanlan.zhihu.com/api/columns/" + ids[i];
      OkHttpUtils.get().url(API.BASE_ZHUANLAN + ids[i]).build().execute(callback);
      //final Call<Zhuanlan> zhuanlanCall = zhuanlanService.getZhuanlan(ids[i]);
      //zhuanlanCall.enqueue(new Callback<Zhuanlan>() {
      //  @Override public void onResponse(Call<Zhuanlan> call, Response<Zhuanlan> response) {
      //    zhuanlanList.add(response.body());
      //    if (response.body() != null) {
      //      Log.i(TAG, response.body().getName());
      //    } else {
      //      Log.i(TAG, "错误");
      //    }
      //    if (zhuanlanList.size() == ids.length) {
      //      zhuanlanPresenter.setZhuanlan(zhuanlanList);
      //      zhuanlanList.clear();
      //      listener.onSuccess();
      //    }
      //  }
      //
      //  @Override public void onFailure(Call<Zhuanlan> call, Throwable t) {
      //    listener.onFailure("错误", (Exception)t);
      //  }
      //});
      //zhuanlanService.getZhuanlan(ids[i])
      //    .subscribeOn(Schedulers.io())
      //    .observeOn(AndroidSchedulers.mainThread())
      //    .subscribe(new Subscriber<Zhuanlan>() {
      //      @Override public void onCompleted() {
      //        //Log.i(TAG, ids[i] + "成功");
      //        listener.onSuccess();
      //      }
      //
      //      @Override public void onError(Throwable e) {
      //        listener.onFailure("加载错误", (Exception) e);
      //      }
      //
      //      @Override public void onNext(Zhuanlan zhuanlan) {
      //        if (zhuanlan == null) {
      //          Log.i(TAG, "zhuanlan为空");
      //        } else {
      //          Log.i(TAG, zhuanlan.getName() + "-->" + zhuanlan.getAvatarUrl());
      //        }
      //        zhuanlanList.add(zhuanlan);
      //        if (zhuanlanList.size() == ids.length) {
      //          zhuanlanPresenter.setZhuanlan(zhuanlanList);
      //          zhuanlanList.clear();
      //        }
      //      }
      //    });
    }
  }
}

package com.oeasy9999.zhihudemo.mvp.presenter;

import android.util.Log;
import com.oeasy9999.zhihudemo.API;
import com.oeasy9999.zhihudemo.model.entity.Remen;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.utils.JsonUtils;
import com.oeasy9999.zhihudemo.mvp.view.RemenView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class RemenPresenterImpl implements RemenPresenter, OnLoadListener {

  public static final String TAG = "RemenPresenterImpl";
  private Remen remen;
  //private HotNews hotNews;
  //private StoryExtra storyExtra;
  private RemenView remenView;
  private long lastGetTime;
  //private long secondGetTime;
  //private int id;

  public RemenPresenterImpl(RemenView remenView) {
    this.remenView = remenView;
  }

  @Override public void loadHotNews() {
    lastGetTime = System.currentTimeMillis();
    Callback<Remen> hotNewsCallback = new Callback<Remen>() {
      @Override public Remen parseNetworkResponse(Response response) throws Exception {
        Log.i(TAG, Thread.currentThread().getName());
        remen = JsonUtils.parseRemen(response.body().string());
        Log.i(TAG, remen.getRecent().size() + "");
        return remen;
      }

      @Override public void onError(Call call, Exception e) {
        if (System.currentTimeMillis() - lastGetTime < 3000) {
          OkHttpUtils.get().url(API.REMEN_HOT).build().execute(this);
          return;
        }
        onFailure("加载失败！", e);
      }

      @Override public void onResponse(Remen response) {
        remenView.showRemen(remen.getRecent());
        onSuccess();
      }
    };
    OkHttpUtils.get().url(API.REMEN_HOT).build().execute(hotNewsCallback);
  }

  @Override public void onSuccess() {
    remenView.hideProgress();
  }

  @Override public void onFailure(String msg, Exception e) {
    remenView.hideProgress();
    remenView.showLoadFailMsg();
  }
}

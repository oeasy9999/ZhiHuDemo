package com.oeasy9999.zhihudemo.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.oeasy9999.zhihudemo.API;
import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.mvp.OnLoadSplashListener;
import com.oeasy9999.zhihudemo.mvp.presenter.SplashPresenter;
import com.oeasy9999.zhihudemo.mvp.utils.JsonUtils;
import com.oeasy9999.zhihudemo.ui.activity.MainActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class SplashModelImple implements SplashModel {

  private SplashPresenter presenter;
  private Context mContext;

  public SplashModelImple(Context context) {
    this.mContext = context;
  }

  @Override public void getSplashImage(final OnLoadSplashListener listener) {
    Callback<SplashImage> callback = new Callback<SplashImage>() {
      @Override public SplashImage parseNetworkResponse(Response response) throws Exception {
        return JsonUtils.parseSplash(response.body().string());
      }

      @Override public void onError(Call call, Exception e) {
        listener.onFailure("load splashimage failure", e);
      }

      @Override public void onResponse(SplashImage response) {
        listener.onSuccess(response);
      }
    };
    OkHttpUtils.get().url(API.SPLASH).build().execute(callback);
  }

  @Override public void showAnimator(Context context, View view) {
    ScaleAnimation scaleAnimation =
        new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f);
    scaleAnimation.setFillAfter(true);//设置动画结束后不跳转到动画头针
    scaleAnimation.setDuration(3000);
    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {
      }

      @Override public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
        ((Activity)mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        ((Activity)mContext).finish();
      }

      @Override public void onAnimationRepeat(Animation animation) {
      }
    });
    view.startAnimation(scaleAnimation);
  }
}

package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.SplashPresenterImpl;
import com.oeasy9999.zhihudemo.service.ApiService;
import com.oeasy9999.zhihudemo.service.SplashService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class SplashModelImple implements SplashModel {

  public static final String TAG = "SplashModelImple";
  //private Context mContext;
  private SplashPresenterImpl splashPresenter;

  public SplashModelImple(SplashPresenterImpl splashPresenter) {
    this.splashPresenter = splashPresenter;
  }

  @Override public void getSplashImage(final OnLoadListener listener) {
    SplashService splashService = ApiService.createApiService().create(SplashService.class);
    splashService.getSplashImage()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<SplashImage>() {
          @Override public void onCompleted() {
            listener.onSuccess();
          }

          @Override public void onError(Throwable e) {
            listener.onFailure("load splashimage failed!", (Exception) e);
          }

          @Override public void onNext(SplashImage splashImage) {
            splashPresenter.setSplashImage(splashImage);
          }
        });
    //Callback<SplashImage> callback = new Callback<SplashImage>() {
    //  @Override public SplashImage parseNetworkResponse(Response response) throws Exception {
    //    Log.i(TAG, Thread.currentThread().getName());
    //    return JsonUtils.parseSplash(response.body().string());
    //  }
    //
    //  @Override public void onError(Call call, Exception e) {
    //    listener.onFailure("load splashimage failure", e);
    //  }
    //
    //  @Override public void onResponse(SplashImage response) {
    //    Log.i(TAG, Thread.currentThread().getName());
    //    listener.onSuccess(response);
    //  }
    //};
    //OkHttpUtils.get().url(API.SPLASH).build().execute(callback);
  }

  //@Override public void showAnimator(Context context, View view) {
  //  ScaleAnimation scaleAnimation =
  //      new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
  //          Animation.RELATIVE_TO_SELF, 0.5f);
  //  scaleAnimation.setFillAfter(true);//设置动画结束后不跳转到动画头针
  //  scaleAnimation.setDuration(3000);
  //  scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
  //    @Override public void onAnimationStart(Animation animation) {
  //    }
  //
  //    @Override public void onAnimationEnd(Animation animation) {
  //      Intent intent = new Intent(mContext, MainActivity.class);
  //      mContext.startActivity(intent);
  //      ((Activity)mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  //      ((Activity)mContext).finish();
  //    }
  //
  //    @Override public void onAnimationRepeat(Animation animation) {
  //    }
  //  });
  //  view.startAnimation(scaleAnimation);
  //}
}

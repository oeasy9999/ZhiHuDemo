package com.oeasy9999.zhihudemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.MainActivity;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.api.ApiService;
import com.oeasy9999.zhihudemo.api.service.SplashService;
import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.squareup.picasso.Picasso;
import retrofit2.Call;

/**
 * Created by oeasy9999 on 2016/8/19.
 */
public class SplashActivity extends BaseNetworkActivity {

  @Bind(R.id.image_splash) ImageView mImageSplash;
  @Bind(R.id.txt_copyright) TextView mTxtCopyright;
  private static final int SHOW_TIME = 3000;
  private SplashService mSplashService;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    ButterKnife.bind(this);
    getSplashImage();
    showAnimator();
  }

  private void getSplashImage() {
    mSplashService = ApiService.createSplashService();
    Call<SplashImage> splashImageCall = mSplashService.getSplashImage();
    networkQueue().enqueue(splashImageCall);
  }

  @Override public void respondSuccess(Object data) {
    //super.respondSuccess(data);
    Class type = data.getClass();
    if (type == SplashImage.class) {
      SplashImage splashImage = (SplashImage) data;
      Picasso.with(this).load(splashImage.getSplashImgUrl()).into(mImageSplash);
      mTxtCopyright.setText(splashImage.getCopyrightInfo());
    }
  }

  private void showAnimator() {
    ScaleAnimation scaleAnimation =
        new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f);
    scaleAnimation.setFillAfter(true);//设置动画结束后不跳转到动画头针
    scaleAnimation.setDuration(SHOW_TIME);
    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {
      }

      @Override public void onAnimationEnd(Animation animation) {
        startActivity();
      }

      @Override public void onAnimationRepeat(Animation animation) {
      }
    });
    mImageSplash.startAnimation(scaleAnimation);
  }

  private void startActivity() {
    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
    startActivity(intent);
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    finish();
  }
}

package com.oeasy9999.zhihudemo.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.SplashImage;
import com.oeasy9999.zhihudemo.mvp.presenter.SplashPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.SplashPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.SplashView;

/**
 * Created by oeasy9999 on 2016/8/19.
 */
public class SplashActivity extends BaseActivity implements SplashView{

  @Bind(R.id.image_splash) ImageView mImageSplash;
  @Bind(R.id.txt_copyright) TextView mTxtCopyright;
  private SplashPresenter mSplashPresenter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    ButterKnife.bind(this);
    //getSplashImage();
    mSplashPresenter = new SplashPresenterImpl(this, SplashActivity.this);
    mSplashPresenter.loadSplashImage();
    mSplashPresenter.animator(mImageSplash);
    //showAnimator();
  }

  @Override public void showSplashImage(SplashImage splashImage) {
    Glide.with(this).load(splashImage.getImg()).into(mImageSplash);
    mTxtCopyright.setText(splashImage.getText());
  }

  //private void showAnimator() {
  //  ScaleAnimation scaleAnimation =
  //      new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
  //          Animation.RELATIVE_TO_SELF, 0.5f);
  //  scaleAnimation.setFillAfter(true);//设置动画结束后不跳转到动画头针
  //  scaleAnimation.setDuration(SHOW_TIME);
  //  scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
  //    @Override public void onAnimationStart(Animation animation) {
  //    }
  //
  //    @Override public void onAnimationEnd(Animation animation) {
  //      startActivity();
  //    }
  //
  //    @Override public void onAnimationRepeat(Animation animation) {
  //    }
  //  });
  //  mImageSplash.startAnimation(scaleAnimation);
  //}
  //
  //private void startActivity() {
  //  Intent intent = new Intent(SplashActivity.this, MainActivity.class);
  //  startActivity(intent);
  //  overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
  //  finish();
  //}
}

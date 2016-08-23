package com.oeasy9999.zhihudemo;

import android.support.annotation.StringRes;
import android.widget.Toast;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.smartydroid.android.starter.kit.account.Account;
import com.smartydroid.android.starter.kit.app.StarterKitApp;
import com.smartydroid.android.starter.kit.retrofit.RetrofitBuilder;

/**
 * Created by oeasy9999 on 2016/8/19.
 */
public class ZhiHuApp extends StarterKitApp{

  Toast mToast = null;

  @Override public void onCreate() {
    super.onCreate();
    new RetrofitBuilder.Builder().baseUrl(Profile.API_ENDPOINT).build();
    Fresco.initialize(this);
  }

  @Override public Account accountFromJson(String json) {
    return null;
  }

  public void showToast(String message) {
    if (null == mToast) {
      mToast = new Toast(this);
      mToast.setView(Toast.makeText(this, "", Toast.LENGTH_SHORT).getView());
    }
    mToast.setText(message);
    mToast.show();
  }

  public void showToast(@StringRes int resId) {
    if (null == mToast) {
      mToast = new Toast(this);
      mToast.setView(Toast.makeText(this, "", Toast.LENGTH_SHORT).getView());
    }
    mToast.setText(resId);
    mToast.show();
  }
}

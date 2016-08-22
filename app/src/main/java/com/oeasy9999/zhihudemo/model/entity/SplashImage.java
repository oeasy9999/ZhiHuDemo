package com.oeasy9999.zhihudemo.model.entity;

import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartydroid.android.starter.kit.model.entity.Entity;

/**
 * Created by oeasy9999 on 2016/8/22.
 */
@SuppressWarnings("unused") @JsonIgnoreProperties(ignoreUnknown = true) public class SplashImage extends Entity{

  @JsonProperty("text") private String copyrightInfo;
  @JsonProperty("img") private String splashImgUrl;

  public SplashImage() {}

  public SplashImage(Parcel source) {
    this.copyrightInfo = source.readString();
    this.splashImgUrl = source.readString();
  }

  public static final Creator<SplashImage> CREATOR = new Creator<SplashImage>() {
    @Override public SplashImage createFromParcel(Parcel parcel) {
      return new SplashImage(parcel);
    }

    @Override public SplashImage[] newArray(int i) {
      return new SplashImage[i];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(copyrightInfo);
    parcel.writeString(splashImgUrl);
  }

  public Uri uri() {
    if (TextUtils.isEmpty(splashImgUrl)) return null;
    if (splashImgUrl.startsWith("https://")) {
      return Uri.parse(splashImgUrl);
    }
    return null;
  }

  public String getCopyrightInfo() {
    return copyrightInfo;
  }

  public void setCopyrightInfo(String copyrightInfo) {
    this.copyrightInfo = copyrightInfo;
  }

  public String getSplashImgUrl() {
    return splashImgUrl;
  }

  public void setSplashImgUrl(String splashImgUrl) {
    this.splashImgUrl = splashImgUrl;
  }
}

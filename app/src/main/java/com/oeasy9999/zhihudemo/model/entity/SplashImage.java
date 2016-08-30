package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/8/22.
 */
public class SplashImage implements Serializable {

  @SerializedName("text") private String text;
  @SerializedName("img") private String img;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }
}

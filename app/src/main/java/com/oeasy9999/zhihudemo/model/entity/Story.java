package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/8/24.
 */
public class Story implements Serializable {

  @SerializedName("id") private int id;
  @SerializedName("title") private String title;
  @SerializedName("type") private int type;
  @SerializedName("ga_prefix") private String gaPrefix;
  @SerializedName("images") private String[] images;

  public Story() {
  }

  public Story(int type, String title){
    this.type = type;
    this.title = title;
  }

  public Story(int id, int type) {
    this.id = id;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getGaPrefix() {
    return gaPrefix;
  }

  public void setGaPrefix(String gaPrefix) {
    this.gaPrefix = gaPrefix;
  }

  public String[] getImages() {
    return images;
  }

  public void setImages(String[] images) {
    this.images = images;
  }
}

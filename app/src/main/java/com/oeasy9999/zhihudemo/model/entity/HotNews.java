package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class HotNews implements Serializable {

  @SerializedName("news_id") private int id;
  @SerializedName("url") private String url;
  @SerializedName("thumbnail") private String thumbnail;
  @SerializedName("title") private String title;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}

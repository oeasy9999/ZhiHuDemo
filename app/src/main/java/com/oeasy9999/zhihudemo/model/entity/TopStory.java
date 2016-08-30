package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/8/24.
 */
public class TopStory implements Serializable {

  @SerializedName("id") private int id;
  @SerializedName("title") private String title;
  @SerializedName("image") private String image;

  public TopStory() {}

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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}

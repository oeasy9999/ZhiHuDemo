package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class Zhuti implements Serializable {
  @SerializedName("color") private int color;
  @SerializedName("thumbnail") private String thumbnail;
  @SerializedName("description") private String description;
  @SerializedName("id") private int id;
  @SerializedName("name") private String name;

  public int getColor() {
    return color;
  }

  public void setColor(int color) {
    this.color = color;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

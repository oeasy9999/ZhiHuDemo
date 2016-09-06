package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import com.oeasy9999.zhihudemo.mvp.interf.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public class Theme implements Serializable, Data {
  @SerializedName("stories") private List<Story> stories;
  @SerializedName("description") private String description;
  @SerializedName("name") private String name;
  @SerializedName("image") private String image;

  public List<Story> getStories() {
    return stories;
  }

  public void setStories(List<Story> stories) {
    this.stories = stories;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  //public class Story implements Serializable{
  //  @SerializedName("images") private List<String> images;
  //  @SerializedName("type") private int type;
  //  @SerializedName("id") private int id;
  //  @SerializedName("title") private String title;
  //
  //  public int getId() {
  //    return id;
  //  }
  //
  //  public void setId(int id) {
  //    this.id = id;
  //  }
  //
  //  public String getTitle() {
  //    return title;
  //  }
  //
  //  public void setTitle(String title) {
  //    this.title = title;
  //  }
  //
  //  public int getType() {
  //    return type;
  //  }
  //
  //  public void setType(int type) {
  //    this.type = type;
  //  }
  //
  //  public List<String> getImages() {
  //    return images;
  //  }
  //
  //  public void setImages(List<String> images) {
  //    this.images = images;
  //  }
  //}
}

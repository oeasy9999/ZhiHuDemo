package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/8/31.
 */
public class NewsDetail implements Serializable{

  @SerializedName("id") private int id;
  @SerializedName("body") private String body;
  @SerializedName("image_source") private String imageSource;
  @SerializedName("title") private String title;
  @SerializedName("image") private String image;
  @SerializedName("share_url") private String shareUrl;
  @SerializedName("css") private List<String> css;
  @SerializedName("type") private int type;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getImageSource() {
    return imageSource;
  }

  public void setImageSource(String imageSource) {
    this.imageSource = imageSource;
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

  public String getShareUrl() {
    return shareUrl;
  }

  public void setShareUrl(String shareUrl) {
    this.shareUrl = shareUrl;
  }

  public List<String> getCss() {
    return css;
  }

  public void setCss(List<String> css) {
    this.css = css;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}

package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import com.oeasy9999.zhihudemo.mvp.interf.Data;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/9/12.
 */
public class ZhuanlanComment implements Serializable, Data {
  @SerializedName("liked") private boolean liked;
  @SerializedName("author") private ZhuanlanPost.Author author;
  @SerializedName("content") private String content;
  @SerializedName("createdTime") private String createdTime;
  @SerializedName("likesCount") private int likesCount;

  public String getAvatarUrl() {
    String id = getAuthor().getAvatar().getId();
    String template = getAuthor().getAvatar().getTemplate();
    String url = template.replace("{id}", id).replace("{size}", "l");
    return url;
  }

  public String getAuthorName() {
    return getAuthor().getName();
  }

  public boolean isLiked() {
    return liked;
  }

  public void setLiked(boolean liked) {
    this.liked = liked;
  }

  public ZhuanlanPost.Author getAuthor() {
    return author;
  }

  public void setAuthor(ZhuanlanPost.Author author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(String createdTime) {
    this.createdTime = createdTime;
  }

  public int getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(int likesCount) {
    this.likesCount = likesCount;
  }
}

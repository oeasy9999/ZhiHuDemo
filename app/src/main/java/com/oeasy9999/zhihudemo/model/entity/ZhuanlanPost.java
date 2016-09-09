package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import com.oeasy9999.zhihudemo.mvp.interf.Data;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/9/8.
 */
public class ZhuanlanPost implements Serializable, Data{
  @SerializedName("author") private Author author;
  @SerializedName("commentsCount") private int commentsCount;
  @SerializedName("likesCount") private int likesCount;
  @SerializedName("slug") private long slug;
  @SerializedName("title") private String title;
  @SerializedName("titleImage") private String titleImage;
  @SerializedName("publishedTime") private String publishedTime;

  public Author getAuthor() {
    return author;
  }

  public String getAuthorName() {
    return author.getName();
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public int getCommentsCount() {
    return commentsCount;
  }

  public void setCommentsCount(int commentsCount) {
    this.commentsCount = commentsCount;
  }

  public int getLikesCount() {
    return likesCount;
  }

  public void setLikesCount(int likesCount) {
    this.likesCount = likesCount;
  }

  public long getSlug() {
    return slug;
  }

  public void setSlug(long slug) {
    this.slug = slug;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitleImage() {
    return titleImage;
  }

  public void setTitleImage(String titleImage) {
    this.titleImage = titleImage;
  }

  public String getPublishedTime() {
    return publishedTime;
  }

  public void setPublishedTime(String publishedTime) {
    this.publishedTime = publishedTime;
  }

  class Author implements Serializable {
    @SerializedName("bio")
    private String bio;

    @SerializedName("hash")
    private String hash;

    @SerializedName("description")
    private String description;

    @SerializedName("profileUrl")
    private String profileUrl;

    @SerializedName("avatar")
    private Avatar avatar;

    @SerializedName("slug")
    private String slug;

    @SerializedName("name")
    private String name;

    public String getBio() {
      return bio;
    }

    public void setBio(String bio) {
      this.bio = bio;
    }

    public String getHash() {
      return hash;
    }

    public void setHash(String hash) {
      this.hash = hash;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getProfileUrl() {
      return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
      this.profileUrl = profileUrl;
    }

    public Avatar getAvatar() {
      return avatar;
    }

    public void setAvatar(Avatar avatar) {
      this.avatar = avatar;
    }

    public String getSlug() {
      return slug;
    }

    public void setSlug(String slug) {
      this.slug = slug;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  class Avatar implements Serializable {
    @SerializedName("id") private String id;
    @SerializedName("template") private String template;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTemplate() {
      return template;
    }

    public void setTemplate(String template) {
      this.template = template;
    }
  }
}

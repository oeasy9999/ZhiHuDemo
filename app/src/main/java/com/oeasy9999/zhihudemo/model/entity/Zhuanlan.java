package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import com.oeasy9999.zhihudemo.mvp.interf.Data;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/9/7.
 */
public class Zhuanlan implements Serializable, Data {

  @SerializedName("followersCount") private int followerCount;

  @SerializedName("description") private String description;

  @SerializedName("creator") private Author author;

  @SerializedName("href") private String href;

  @SerializedName("slug") private String slug;

  @SerializedName("name") private String name;

  @SerializedName("url") private String url;

  @SerializedName("avatar") private Avatar avatar;

  @SerializedName("postsCount") private int postCount;

  public int getFollowerCount() {
    return followerCount;
  }

  public void setFollowerCount(int followerCount) {
    this.followerCount = followerCount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Avatar getAvatar() {
    return avatar;
  }

  public void setAvatar(Avatar avatar) {
    this.avatar = avatar;
  }

  public int getPostCount() {
    return postCount;
  }

  public void setPostCount(int postCount) {
    this.postCount = postCount;
  }

  public String getAvatarUrl() {
    String id = avatar.getId();
    String template = avatar.getTemplate();
    String url = template.replace("{id}", id).replace("{size}", "l");
    //String str = "https://pic2.zhimg.com/" + id + "_l.jpg";
    return url;
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
}

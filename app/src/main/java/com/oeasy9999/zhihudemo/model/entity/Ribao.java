package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import com.oeasy9999.zhihudemo.mvp.interf.Data;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by oeasy9999 on 2016/8/24.
 */
public class Ribao implements Serializable, Data {

  @SerializedName("date") private String data;
  @SerializedName("stories") private ArrayList<Story> stories;
  @SerializedName("top_stories") private ArrayList<TopStory> topStories;

  public Ribao() {
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public ArrayList<Story> getStories() {
    return stories;
  }

  public void setStories(ArrayList<Story> stories) {
    this.stories = stories;
  }

  public ArrayList<TopStory> getTopStories() {
    return topStories;
  }

  public void setTopStories(ArrayList<TopStory> topStories) {
    this.topStories = topStories;
  }
}

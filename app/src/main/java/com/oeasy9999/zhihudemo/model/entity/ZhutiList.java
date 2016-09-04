package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiList implements Serializable{

  @SerializedName("limit") private int limit;
  @SerializedName("subscribed") private List<String> subscribed;
  @SerializedName("others") private List<Zhuti> zhutis;

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public List<String> getSubscribed() {
    return subscribed;
  }

  public void setSubscribed(List<String> subscribed) {
    this.subscribed = subscribed;
  }

  public List<Zhuti> getZhutis() {
    return zhutis;
  }

  public void setZhutis(List<Zhuti> zhutis) {
    this.zhutis = zhutis;
  }
}

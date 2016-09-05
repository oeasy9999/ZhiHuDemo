package com.oeasy9999.zhihudemo.model.entity;

import com.google.gson.annotations.SerializedName;
import com.oeasy9999.zhihudemo.mvp.interf.Data;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class Remen implements Serializable, Data {

  @SerializedName("recent") private ArrayList<HotNews> recent;

  public ArrayList<HotNews> getRecent() {
    return recent;
  }

  public void setRecent(ArrayList<HotNews> recent) {
    this.recent = recent;
  }

  @Override public String toString() {
    return "Remen{" +
        "recent=" + recent +
        '}';
  }
}

package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public interface NewsDetailModel {
  void getNewsDetail(int id, OnLoadListener listener);
}

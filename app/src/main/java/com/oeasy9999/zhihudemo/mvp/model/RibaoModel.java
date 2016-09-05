package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadNewsDetailListener;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public interface RibaoModel {
  void getRibao(int type, OnLoadListener listener);
  void getRibaoDetail(int id, OnLoadNewsDetailListener listener);
}

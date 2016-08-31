package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.mvp.interf.OnLoadNewsListener;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadNewsDetailListener;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public interface RibaoModel {
  void getRibao(int type, OnLoadNewsListener listener);
  void getRibaoDetail(int id, OnLoadNewsDetailListener listener);
}

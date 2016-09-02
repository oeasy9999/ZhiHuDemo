package com.oeasy9999.zhihudemo.mvp.interf;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public interface OnLoadListener {
  void onSuccess();
  void onFailure(String msg, Exception e);
}

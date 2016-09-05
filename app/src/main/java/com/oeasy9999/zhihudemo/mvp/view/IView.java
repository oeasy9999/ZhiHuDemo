package com.oeasy9999.zhihudemo.mvp.view;

import com.oeasy9999.zhihudemo.mvp.interf.Data;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public interface IView<T extends Data> {
  void showData(T data);
  void showProgress();
  void hideProgress();
  void showLoadFailMsg();
}

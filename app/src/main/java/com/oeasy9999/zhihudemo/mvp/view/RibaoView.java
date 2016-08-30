package com.oeasy9999.zhihudemo.mvp.view;

import com.oeasy9999.zhihudemo.model.entity.Ribao;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public interface RibaoView {
  void showProgress();
  void showRibao(Ribao ribao);
  void hideProgress();
  void showLoadFailMsg();
}

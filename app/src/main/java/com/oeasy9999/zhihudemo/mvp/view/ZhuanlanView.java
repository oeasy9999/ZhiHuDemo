package com.oeasy9999.zhihudemo.mvp.view;

import com.oeasy9999.zhihudemo.model.entity.Zhuanlan;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/8.
 */
public interface ZhuanlanView {
  void showData(List<Zhuanlan> data);
  void showProgress();
  void hideProgress();
  void showLoadFailMsg();
}

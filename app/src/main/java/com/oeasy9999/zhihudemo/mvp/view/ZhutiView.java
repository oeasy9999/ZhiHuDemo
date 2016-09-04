package com.oeasy9999.zhihudemo.mvp.view;

import com.oeasy9999.zhihudemo.model.entity.Zhuti;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public interface ZhutiView {
  void showZhutiList(List<Zhuti> zhutiList);
  void showProgress();
  void hideProgress();
  void showLoadFailMsg();
}

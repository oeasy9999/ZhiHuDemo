package com.oeasy9999.zhihudemo.mvp.view;

import com.oeasy9999.zhihudemo.model.entity.HotNews;
import java.util.ArrayList;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public interface RemenView {
  void showRemen(ArrayList<HotNews> hotNewes);
  void showProgress();
  void hideProgress();
  void showLoadFailMsg();
}

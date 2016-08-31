package com.oeasy9999.zhihudemo.mvp.view;

import com.oeasy9999.zhihudemo.model.entity.NewsDetail;

/**
 * Created by oeasy9999 on 2016/8/31.
 */
public interface NewsDetailView {
  void showProgress();
  void showDetail(NewsDetail newsDetail);
  void hidProgress();
  void showLoadFailed(String msg);
}

package com.oeasy9999.zhihudemo.mvp.interf;

import com.oeasy9999.zhihudemo.model.entity.NewsDetail;

/**
 * Created by oeasy9999 on 2016/8/31.
 */
public interface OnLoadNewsDetailListener {
  void onSuccess(NewsDetail newsDetail);
  void onFailure(String msg, Exception e);
}

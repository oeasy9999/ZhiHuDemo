package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.mvp.interf.NewsItem;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;

/**
 * Created by oeasy9999 on 2016/9/4.
 */
public interface NewsModel<T extends NewsItem> {
  void getNews(OnLoadListener listener);
}

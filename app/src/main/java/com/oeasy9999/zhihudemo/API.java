package com.oeasy9999.zhihudemo;

/**
 * Created by oeasy9999 on 2016/8/22.
 */
public interface API {
  String API_ENDPOINT = "http://news-at.zhihu.com/api/";
  public static final int TYPE_LATEST = 0;
  public static final int TYPE_BEFORE = 1;

  public static final String BASE_URL = "http://news-at.zhihu.com/api/4/news/";
  public static final String RIBAO_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
  public static final String RIBAO_BEFORE = "http://news-at.zhihu.com/api/4/news/before/";

  public static final String SPLASH = "http://news-at.zhihu.com/api/4/start-image/1080*1920";

  public static final String REMEN_HOT = "http://news-at.zhihu.com/api/3/news/hot";
  public static final String STORY_EXTRA = "http://news-at.zhihu.com/api/4/story-extra/";
}

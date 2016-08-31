package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.API;
import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadNewsListener;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadNewsDetailListener;
import com.oeasy9999.zhihudemo.mvp.utils.JsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class RibaoModelImple implements RibaoModel {

  private static final int GET_DURATION = 3000;
  private int type;
  private long lastGetTime;
  private Ribao ribao;

  /**
   * 加载日报列表
   * @param type
   * @param listener
   */
  @Override public void getRibao(int type, final OnLoadNewsListener listener) {
    this.type = type;
    lastGetTime = System.currentTimeMillis();

    Callback<Ribao> callback = new Callback<Ribao>() {

      //List<Story> stories;

      @Override public Ribao parseNetworkResponse(Response response) throws Exception {
        ribao = JsonUtils.parseRibao(response.body().string());
        //stories = ribao.getStories();
        //Log.i("嘿嘿嘿", stories.size()+"");
        return ribao;
      }

      @Override public void onError(Call call, Exception e) {
        if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
          getData(this);
          return;
        }
        //e.printStackTrace();
        listener.onFailure("load zhihu news failed", e);
      }

      @Override public void onResponse(Ribao response) {
        listener.onSuccess(ribao);
      }
    };
    getData(callback);
  }

  /**
   * 加载日报详情
   * @param id
   * @param listener
   */
  @Override public void getRibaoDetail(final int id, final OnLoadNewsDetailListener listener) {
    lastGetTime = System.currentTimeMillis();
    Callback<NewsDetail> callback = new Callback<NewsDetail>() {

      private NewsDetail newsDetail;

      @Override public NewsDetail parseNetworkResponse(Response response) throws Exception {
        newsDetail = JsonUtils.parseNewsDetail(response.body().string());
        return newsDetail;
      }

      @Override public void onError(Call call, Exception e) {
        if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
          OkHttpUtils.get().url(API.BASE_URL + id).build().execute(this);
          return;
        }
        e.printStackTrace();
        listener.onFailure("load newsdetail failed", e);
      }

      @Override public void onResponse(NewsDetail response) {
        listener.onSuccess(newsDetail);
      }
    };
    OkHttpUtils.get().url(API.BASE_URL + id).build().execute(callback);
  }

  private void getData(Callback callback) {
    if (type == API.TYPE_LATEST) {
      OkHttpUtils.get().url(API.RIBAO_LATEST).build().execute(callback);
    } else if (type == API.TYPE_BEFORE) {
      //String date = parseStandarDate(new Date());
      String date = ribao.getData();
      OkHttpUtils.get().url(API.RIBAO_BEFORE + date).build().execute(callback);
    }
  }

  //private String parseStandarDate(Date date) {
  //  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
  //  formatter.setLenient(false);
  //  return formatter.format(date);
  //}
}

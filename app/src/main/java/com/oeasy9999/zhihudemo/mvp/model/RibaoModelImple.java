package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.API;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.mvp.OnLoadNewsListener;
import com.oeasy9999.zhihudemo.mvp.utils.JsonUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class RibaoModelImple implements RibaoModel {

  private static final int GET_DURATION = 2000;
  private int type;
  private long lastGetTime;
  private Ribao ribao;

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

  private void getData(Callback callback) {
    if (type == API.TYPE_LATEST) {
      OkHttpUtils.get().url(API.RIBAO_LATEST).build().execute(callback);
    } else if (type == API.TYPE_BEFORE) {
      //String date = parseStandarDate(new Date());
      String date = ribao.getData();
      OkHttpUtils.get().url(API.RIBAO_BEFORE + date).build().execute(callback);
    }
  }

  private String parseStandarDate(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    formatter.setLenient(false);
    return formatter.format(date);
  }
}

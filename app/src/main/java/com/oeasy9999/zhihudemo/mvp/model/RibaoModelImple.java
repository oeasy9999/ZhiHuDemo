package com.oeasy9999.zhihudemo.mvp.model;

import com.oeasy9999.zhihudemo.service.API;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.presenter.RibaoPresenterImpl;
import com.oeasy9999.zhihudemo.service.ApiService;
import com.oeasy9999.zhihudemo.service.RibaoService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by oeasy9999 on 2016/8/28.
 */
public class RibaoModelImple implements RibaoModel {

  private static final int GET_DURATION = 3000;
  //private int type;
  //private long lastGetTime;
  private Ribao mRibao;

  private RibaoPresenterImpl ribaoPresenter;

  //public RibaoModelImple() {
  //}

  public RibaoModelImple(RibaoPresenterImpl ribaoPresenter) {
    this.ribaoPresenter = ribaoPresenter;
  }

  /*public void setRibao(Ribao ribao) {
    this.mRibao = ribao;
  }*/

  /**
   * 加载日报列表
   */
  @Override public void getRibao(int type, final OnLoadListener listener) {
    //this.type = type;
    RibaoService ribaoService = ApiService.createApiService().create(RibaoService.class);
    if (type == API.TYPE_LATEST) {
      ribaoService.getRibaoLatest()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<Ribao>() {
            @Override public void onCompleted() {
              listener.onSuccess();
            }

            @Override public void onError(Throwable e) {
              listener.onFailure("load news failed", (Exception) e);
            }

            @Override public void onNext(Ribao ribao) {
              //setRibao(ribao);
              mRibao = ribao;
              ribaoPresenter.setRibao(ribao);
            }
          });
    } else if (type == API.TYPE_BEFORE) {
      String data = mRibao.getData();
      ribaoService.getRibaoBefore(data)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Subscriber<Ribao>() {
            @Override public void onCompleted() {
              listener.onSuccess();
            }

            @Override public void onError(Throwable e) {
              listener.onFailure("load news failed", (Exception) e);
            }

            @Override public void onNext(Ribao ribao) {
              //setRibao(ribao);
              mRibao = ribao;
              ribaoPresenter.setRibao(ribao);
            }
          });
    }
    //lastGetTime = System.currentTimeMillis();
    //
    //Callback<Ribao> callback = new Callback<Ribao>() {
    //
    //  //List<Story> stories;
    //
    //  @Override public Ribao parseNetworkResponse(Response response) throws Exception {
    //    ribao = JsonUtils.parseRibao(response.body().string());
    //    //stories = ribao.getStories();
    //    //Log.i("嘿嘿嘿", stories.size()+"");
    //    return ribao;
    //  }
    //
    //  @Override public void onError(Call call, Exception e) {
    //    if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
    //      getData(this);
    //      return;
    //    }
    //    //e.printStackTrace();
    //    listener.onFailure("load zhihu news failed", e);
    //  }
    //
    //  @Override public void onResponse(Ribao response) {
    //    listener.onSuccess(response);
    //  }
    //};
    //getData(callback);
  }

  /**
   * 加载日报详情
   */
  //@Override public void getRibaoDetail(final int id, final OnLoadNewsDetailListener listener) {
  //  lastGetTime = System.currentTimeMillis();
  //  Callback<NewsDetail> callback = new Callback<NewsDetail>() {
  //    @Override public NewsDetail parseNetworkResponse(Response response) throws Exception {
  //      return JsonUtils.parseNewsDetail(response.body().string());
  //    }
  //
  //    @Override public void onError(Call call, Exception e) {
  //      if (System.currentTimeMillis() - lastGetTime < GET_DURATION) {
  //        OkHttpUtils.get().url(API.BASE_URL + id).build().execute(this);
  //        return;
  //      }
  //      e.printStackTrace();
  //      listener.onFailure("load newsdetail failed", e);
  //    }
  //
  //    @Override public void onResponse(NewsDetail response) {
  //      listener.onSuccess(response);
  //    }
  //  };
  //  OkHttpUtils.get().url(API.BASE_URL + id).build().execute(callback);
  //}

  //private void getData(Callback callback) {
  //  if (type == API.TYPE_LATEST) {
  //    OkHttpUtils.get().url(API.RIBAO_LATEST).build().execute(callback);
  //  } else if (type == API.TYPE_BEFORE) {
  //    //String date = parseStandarDate(new Date());
  //    String date = ribao.getData();
  //    OkHttpUtils.get().url(API.RIBAO_BEFORE + date).build().execute(callback);
  //  }
  //}

  //private String parseStandarDate(Date date) {
  //  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
  //  formatter.setLenient(false);
  //  return formatter.format(date);
  //}
}

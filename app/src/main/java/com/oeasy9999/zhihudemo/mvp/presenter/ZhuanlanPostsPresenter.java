package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.ZhuanlanPostsModel;
import com.oeasy9999.zhihudemo.ui.activity.ZhuanlanPostsActivity;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/8.
 */
public class ZhuanlanPostsPresenter implements OnLoadListener {

  private List<ZhuanlanPost> zhuanlanPosts;
  private ZhuanlanPostsModel zhuanlanPostsModel;
  private ZhuanlanPostsActivity zhuanlanPostsView;

  public ZhuanlanPostsPresenter(ZhuanlanPostsActivity zhuanlanPostsView) {
    this.zhuanlanPostsView = zhuanlanPostsView;
    this.zhuanlanPostsModel = new ZhuanlanPostsModel(this);
  }

  public void setZhuanlanPosts(List<ZhuanlanPost> zhuanlanPosts) {
    this.zhuanlanPosts = zhuanlanPosts;
  }

  public void loadData(String slug, int offset) {
    zhuanlanPostsView.showProgress();
    zhuanlanPostsModel.getZhuanlanPosts(slug, offset, this);
  }

  @Override public void onSuccess() {
    zhuanlanPostsView.hideProgress();
    if (zhuanlanPosts != null) {
      zhuanlanPostsView.showData(zhuanlanPosts);
    } else {
      zhuanlanPostsView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    zhuanlanPostsView.hideProgress();
    zhuanlanPostsView.showLoadFailMsg();
  }
}

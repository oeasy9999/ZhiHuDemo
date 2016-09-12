package com.oeasy9999.zhihudemo.mvp.presenter;

import com.oeasy9999.zhihudemo.model.entity.ZhuanlanComment;
import com.oeasy9999.zhihudemo.mvp.interf.OnLoadListener;
import com.oeasy9999.zhihudemo.mvp.model.ZhuanlanCommentModel;
import com.oeasy9999.zhihudemo.ui.activity.ZhuanlanCommentActivity;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/12.
 */
public class ZhuanlanCommentPresenter implements OnLoadListener {

  private List<ZhuanlanComment> zhuanlanComments;
  private ZhuanlanCommentActivity zhuanlanCommentView;
  private ZhuanlanCommentModel zhuanlanCommentModel;

  public ZhuanlanCommentPresenter(ZhuanlanCommentActivity zhuanlanCommentActivity) {
    this.zhuanlanCommentView = zhuanlanCommentActivity;
    this.zhuanlanCommentModel = new ZhuanlanCommentModel(this);
  }

  public void setZhuanlanComments(List<ZhuanlanComment> zhuanlanComments) {
    this.zhuanlanComments = zhuanlanComments;
  }

  public void loadData(int slug, int offset) {
    zhuanlanCommentView.showProgress();
    zhuanlanCommentModel.getZhuanlanComments(slug, offset, this);
  }

  @Override public void onSuccess() {
    zhuanlanCommentView.hideProgress();
    if (zhuanlanComments != null) {
      zhuanlanCommentView.showData(zhuanlanComments);
    } else {
      zhuanlanCommentView.showLoadFailMsg();
    }
  }

  @Override public void onFailure(String msg, Exception e) {
    zhuanlanCommentView.hideProgress();
    zhuanlanCommentView.showLoadFailMsg();
  }
}

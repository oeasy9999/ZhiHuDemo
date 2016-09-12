package com.oeasy9999.zhihudemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanComment;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanCommentPresenter;
import com.oeasy9999.zhihudemo.ui.adapter.ZhuanlanCommentAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/12.
 */
public class ZhuanlanCommentActivity extends BaseActivity {

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  private static final String TAG = "ZhuanlanComment测试";
  private int slug;
  private int commentsCount;
  private ZhuanlanCommentPresenter zhuanlanCommentPresenter;
  private LinearLayoutManager layoutManager;
  private ZhuanlanCommentAdapter zhuanlanCommentAdapter;
  private List<ZhuanlanComment> list = new ArrayList<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zhuanlan_comment);
    ButterKnife.bind(this);
    initToolbar();
    initContent();
    initViews();
  }

  private void initToolbar() {
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  private void initContent() {
    slug = getIntent().getIntExtra("slug", 0);
    commentsCount = getIntent().getIntExtra("commentsCount", 0);
    zhuanlanCommentPresenter = new ZhuanlanCommentPresenter(this);
    zhuanlanCommentPresenter.loadData(slug, 0);
  }

  private void initViews() {
    layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
      boolean isSlidingToLast = false;
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState != RecyclerView.SCROLL_STATE_IDLE) return;
        int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
        int totalItemCount = layoutManager.getItemCount();
        Log.i(TAG, lastVisibleItem + "-->" + totalItemCount);
        if ((lastVisibleItem + 1 == totalItemCount) && isSlidingToLast) {
          if (list.size() < commentsCount) {
            int offset = list.size();
            zhuanlanCommentPresenter.loadData(slug, offset);
          } else {
            Snackbar.make(mRecyclerView, "没有更多评论了！", Snackbar.LENGTH_SHORT).show();
          }
        }
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isSlidingToLast = dy > 0;
      }
    });
  }

  public void showData(List<ZhuanlanComment> zhuanlanComments) {
    list.addAll(zhuanlanComments);
    if (zhuanlanCommentAdapter == null) {
      zhuanlanCommentAdapter = new ZhuanlanCommentAdapter(this, list);
      mRecyclerView.setAdapter(zhuanlanCommentAdapter);
    } else {
      zhuanlanCommentAdapter.notifyDataSetChanged();
    }
  }

  public void showProgress() {
  }

  public void hideProgress() {

  }

  public void showLoadFailMsg() {
  }
}

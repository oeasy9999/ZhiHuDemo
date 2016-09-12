package com.oeasy9999.zhihudemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanPostsPresenter;
import com.oeasy9999.zhihudemo.ui.adapter.ZhuanlanPostsAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/8.
 */
public class ZhuanlanPostsActivity extends BaseActivity implements
    SwipeRefreshLayout.OnRefreshListener, OnItemClickListener{

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;

  private static final String TAG = "ZhuanlanPostsActivity测试";
  private ZhuanlanPostsPresenter zhuanlanPostsPresenter;
  private String slug;
  private LinearLayoutManager layoutManager;
  private ZhuanlanPostsAdapter adapter;
  private int offset;
  List<ZhuanlanPost> zhuanlanPosts = new ArrayList<>();
  private int postsCount;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zhuanlan_posts);
    ButterKnife.bind(this);
    initToolbar();
    initContent();
  }

  private void initToolbar() {
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  private void initContent() {
    Intent intent = getIntent();
    slug = intent.getStringExtra("slug");
    String tilte = intent.getStringExtra("title");
    postsCount = intent.getIntExtra("postscount", 20);
    getSupportActionBar().setTitle(tilte);

    zhuanlanPostsPresenter = new ZhuanlanPostsPresenter(this);
    initView();
  }

  private void initView() {
    mRecyclerView.setHasFixedSize(true);
    mSwipeRefreshWidget.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark,
        R.color.colorAccent);
    mSwipeRefreshWidget.setOnRefreshListener(this);
    layoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
          onListScrolled();
        }
      }
    });
    onRefresh();
  }

  private void onListScrolled() {
    int lastPosition = layoutManager.findLastVisibleItemPosition();
    if (lastPosition + 1 == adapter.getItemCount() && adapter.showFooter) {
      offset = zhuanlanPosts.size();
      zhuanlanPostsPresenter.loadData(slug, offset);//----------
    }
  }

  public void showData(List<ZhuanlanPost> data) {
    zhuanlanPosts.addAll(data);
    if (zhuanlanPosts.size() == 0) return;
    Log.i(TAG, zhuanlanPosts.size()+"-->"+postsCount);
    //adapter.showFooter = true;
    if (adapter != null && postsCount <= zhuanlanPosts.size()) adapter.showFooter = false;
    if (adapter == null) {
      adapter = new ZhuanlanPostsAdapter(ZhuanlanPostsActivity.this, zhuanlanPosts, this);
      mRecyclerView.setAdapter(adapter);
    } else {
      adapter.notifyDataSetChanged();
    }
    mSwipeRefreshWidget.setRefreshing(false);
  }

  public void showProgress() {
    mSwipeRefreshWidget.setRefreshing(true);
  }

  public void hideProgress() {
    mSwipeRefreshWidget.setRefreshing(false);
  }

  public void showLoadFailMsg() {
  }

  @Override public void onRefresh() {
    if (zhuanlanPosts != null || zhuanlanPosts.size() > 0) {
      zhuanlanPosts.clear();
    }
    zhuanlanPostsPresenter.loadData(slug, 0);
  }

  @Override public void onItemClick(RecyclerView.ViewHolder holder) {
    if (holder instanceof ZhuanlanPostsAdapter.ItemViewHolder) {
      ZhuanlanPostsAdapter.ItemViewHolder itemViewHolder = (ZhuanlanPostsAdapter.ItemViewHolder) holder;
      Intent intent = new Intent(this, ZhuanlanDetailActivity.class);
      intent.putExtra("story", itemViewHolder.zhuanlanPost);
      startActivity(intent);
    }
  }
}

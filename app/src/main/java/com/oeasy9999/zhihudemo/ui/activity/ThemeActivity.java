package com.oeasy9999.zhihudemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.Story;
import com.oeasy9999.zhihudemo.model.entity.Theme;
import com.oeasy9999.zhihudemo.model.entity.Zhuti;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.mvp.presenter.NewsDetailPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.ThemePresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;
import com.oeasy9999.zhihudemo.ui.adapter.ThemeAdapter;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/9/6.
 */
public class ThemeActivity extends BaseActivity
    implements IView<Theme>, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener{

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.img_theme) ImageView mImgTheme;
  @Bind(R.id.txt_theme_title) TextView mTxtThemeTitle;
  @Bind(R.id.txt_theme_subtitle) TextView mTxtThemeSubtitle;
  @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;

  private NewsDetailPresenter themePresenter;
  private int id;
  private static Zhuti zhuti;
  private Serializable serializable;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_theme_detail);
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
    Serializable serializable = getIntent().getSerializableExtra("theme");
    if (serializable instanceof Zhuti) {
      Zhuti zhuti = (Zhuti) serializable;
      id = zhuti.getId();
      mCollapsingToolbar.setTitle(zhuti.getName());
      ImageUtils.loadWithPlaceholder(this, zhuti.getThumbnail(), R.drawable.placeholder, mImgTheme);
      //mTxtThemeTitle.setText(zhuti.getName());
      mTxtThemeSubtitle.setText(zhuti.getDescription());
    }
    themePresenter = new ThemePresenterImpl(this);
    initView();
  }

  private void initView() {
    mRecyclerView.setHasFixedSize(true);
    mSwipeRefreshWidget.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark,
        R.color.colorAccent);
    mSwipeRefreshWidget.setOnRefreshListener(this);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    onRefresh();
  }

  @Override public void showData(Theme data) {
    ThemeAdapter adapter = new ThemeAdapter(ThemeActivity.this, data.getStories(), this);
    mRecyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }

  @Override public void showProgress() {
    mSwipeRefreshWidget.setRefreshing(true);
  }

  @Override public void hideProgress() {
    mSwipeRefreshWidget.setRefreshing(false);
  }

  @Override public void showLoadFailMsg() {
    Toast.makeText(this, "加载失败！", Toast.LENGTH_SHORT).show();
  }

  @Override public void onItemClick(RecyclerView.ViewHolder holder) {
    if (holder instanceof ThemeAdapter.ItemViewHolder) {
      ThemeAdapter.ItemViewHolder itemViewHolder = (ThemeAdapter.ItemViewHolder) holder;
      Story story = itemViewHolder.story;
      Intent intent = new Intent(this, NewsDetailActivity.class);
      intent.putExtra("story", story);
      startActivity(intent);
    }
  }

  @Override public void onRefresh() {
    themePresenter.loadNewsDetail(id);
  }
}

package com.oeasy9999.zhihudemo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import com.oeasy9999.zhihudemo.model.entity.Story;
import com.oeasy9999.zhihudemo.mvp.presenter.NewsDetailPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.NewsDetailPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.NewsDetailView;

/**
 * Created by oeasy9999 on 2016/8/31.
 */
public class NewsDetailActivity extends BaseActivity implements NewsDetailView {

  @Bind(R.id.img_news_detail) ImageView mImgNewsDetail;
  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
  @Bind(R.id.progress) ProgressBar mProgress;
  @Bind(R.id.web_container) FrameLayout mWebContainer;
  @Bind(R.id.fab) FloatingActionButton fab;

  private NewsDetailPresenter mNewsDetailPresenter;
  private NewsDetail mNewsDetail;
  private Story story;
  private WebView mWebView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news_detail);
    ButterKnife.bind(this);
    initToolbar();
    initView();
  }

  private void initToolbar() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  private void initView() {
    story = (Story) getIntent().getSerializableExtra("story");
    mCollapsingToolbar.setTitle(story.getTitle());
    Glide.with(this).load(story.getImages()[0]).into(mImgNewsDetail);

    mNewsDetailPresenter = new NewsDetailPresenterImpl(this);
    initWebView();
    mNewsDetailPresenter.loadNewsDetail(story.getId());
  }

  @SuppressLint("SetJavaScriptEnabled") private void initWebView() {
    mWebView = new WebView(this);
    mWebContainer.addView(mWebView);
    mWebView.setVisibility(View.INVISIBLE);
    WebSettings settings = mWebView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    settings.setAppCacheEnabled(true);
    settings.setDomStorageEnabled(true);
    mWebView.setWebChromeClient(new WebChromeClient(){
      @Override public void onProgressChanged(final WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if(newProgress == 100) {
          view.postDelayed(new Runnable() {
            @Override public void run() {
              view.setVisibility(View.VISIBLE);
              hidProgress();
            }
          }, 300);
        }
      }
    });
  }

  @Override public void showProgress() {
    mProgress.setVisibility(View.VISIBLE);
  }

  @Override public void showDetail(NewsDetail newsDetail) {
    mNewsDetail = newsDetail;
    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
    String html = "<html><head>" + css + "</head><body>" + newsDetail.getBody() + "</body></html>";
    html = html.replace("<div class=\"img-place-holder\">", "");
    mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
  }

  @Override public void hidProgress() {
    mProgress.setVisibility(View.GONE);
  }

  @Override public void showLoadFailed(String msg) {

  }
}

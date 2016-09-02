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
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.HotNews;
import com.oeasy9999.zhihudemo.model.entity.NewsDetail;
import com.oeasy9999.zhihudemo.model.entity.Story;
import com.oeasy9999.zhihudemo.model.entity.TopStory;
import com.oeasy9999.zhihudemo.mvp.presenter.NewsDetailPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.NewsDetailPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.NewsDetailView;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import java.io.Serializable;

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
  private WebView mWebView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news_detail);
    ButterKnife.bind(this);
    initToolbar();
    initView();
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

  private void initView() {
    Serializable serializable = getIntent().getSerializableExtra("story");
    int id = 0;
    if (serializable instanceof Story) {
      Story story = (Story) serializable;
      id = story.getId();
      mCollapsingToolbar.setTitle(story.getTitle());
      ImageUtils.loadWithPlaceholder(this, story.getImages()[0], R.drawable.placeholder, mImgNewsDetail);
    } else if (serializable instanceof TopStory){
      TopStory topStory = (TopStory) serializable;
      id = topStory.getId();
      mCollapsingToolbar.setTitle(topStory.getTitle());
      ImageUtils.loadWithPlaceholder(this, topStory.getImage(), R.drawable.placeholder, mImgNewsDetail);
    } else if (serializable instanceof HotNews) {
      HotNews hotNews = (HotNews) serializable;
      id = hotNews.getId();
      mCollapsingToolbar.setTitle(hotNews.getTitle());
      ImageUtils.loadWithPlaceholder(this, hotNews.getThumbnail(), R.drawable.placeholder, mImgNewsDetail);
    }
    mNewsDetailPresenter = new NewsDetailPresenterImpl(this);
    initWebView();
    mNewsDetailPresenter.loadNewsDetail(id);
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
    initFab();
  }

  private void initFab() {
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Toast.makeText(NewsDetailActivity.this, "soon coming", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override public void hidProgress() {
    mProgress.setVisibility(View.GONE);
  }

  @Override public void showLoadFailed(String msg) {

  }

  @Override protected void onDestroy() {
    super.onDestroy();
    System.exit(0);
  }
}

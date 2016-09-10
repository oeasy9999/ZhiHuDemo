package com.oeasy9999.zhihudemo.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanDetailPresenter;
import com.oeasy9999.zhihudemo.mvp.view.IView;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import com.oeasy9999.zhihudemo.utils.TimeUtils;
import java.io.Serializable;

/**
 * Created by oeasy9999 on 2016/9/9.
 */
public class ZhuanlanDetailActivity extends BaseActivity implements IView<ZhuanlanPost> {

  @Bind(R.id.img_news_detail) ImageView mImgNewsDetail;
  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
  @Bind(R.id.app_bar) AppBarLayout mAppBar;
  @Bind(R.id.progress) ProgressBar mProgress;
  @Bind(R.id.web_container) WebView mWebContainer;
  @Bind(R.id.fab) FloatingActionButton fab;

  private static final String TAG = "ZhuanlanDetail测试";
  @Bind(R.id.img_avatar) ImageView mImgAvatar;
  @Bind(R.id.txt_zhuanlan_detail_name) TextView mTxtZhuanlanDetailName;
  @Bind(R.id.txt_zhuanlan_detail_publishtime) TextView mTxtZhuanlanDetailPublishtime;
  private ZhuanlanDetailPresenter zhuanlanDetailPresenter;
  //private WebView mWebView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_zhuanlan_detail);
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
      @Override public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  private void initContent() {
    Serializable serializable = getIntent().getSerializableExtra("story");
    if (serializable instanceof ZhuanlanPost) {
      ZhuanlanPost zhuanlanPost = (ZhuanlanPost) serializable;
      mCollapsingToolbar.setTitle(zhuanlanPost.getTitle());
      ImageUtils.load(this, zhuanlanPost.getAvatarUrl(), mImgAvatar);
      mTxtZhuanlanDetailName.setText(zhuanlanPost.getAuthorName());
      mTxtZhuanlanDetailPublishtime.setText(TimeUtils.convertPublishTime(zhuanlanPost.getPublishedTime()));
      int slug = zhuanlanPost.getSlug();
      if (zhuanlanPost.getTitleImage() != null || !zhuanlanPost.getTitleImage().equals("")) {
        ImageUtils.loadWithPlaceholder(this, zhuanlanPost.getTitleImage(), R.drawable.placeholder,
            mImgNewsDetail);
      }
      zhuanlanDetailPresenter = new ZhuanlanDetailPresenter(this);
      initWebView();
      Log.i(TAG, slug + "");
      zhuanlanDetailPresenter.loadNewsDetail(slug);
    }
  }

  @SuppressLint("SetJavaScriptEnabled") private void initWebView() {
    //mWebView = new WebView(this);
    //ViewGroup.LayoutParams lp = mWebView.getLayoutParams();
    //mWebView.setPadding(5, 0, 5, 0);
    //mWebContainer.addView(mWebView);
    //mWebView.setVisibility(View.INVISIBLE);
    WebSettings settings = mWebContainer.getSettings();
    settings.setJavaScriptEnabled(true);
    //缩放，设置不能缩放可以防止页面上出现放大和缩小的图标
    settings.setBuiltInZoomControls(false);
    //设置缓存模式
    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    //开启DOM storage API功能
    settings.setDomStorageEnabled(true);
    //开启application Cache功能
    settings.setAppCacheEnabled(false);

    settings.setLoadsImagesAutomatically(true);
    //settings.setDefaultTextEncodingName("UTF-8");
    //settings.setBlockNetworkImage(false);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    //settings.setUseWideViewPort(true);
    //settings.setLoadWithOverviewMode(true);

    mWebContainer.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        mWebContainer.loadUrl(url);
        return true;
      }

      @Override public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        view.setVisibility(View.VISIBLE);
        hideProgress();
      }
    });

    //不调用第三方浏览器即可进行页面反应
    //mWebView.setWebChromeClient(new WebChromeClient() {
    //  @Override public void onProgressChanged(final WebView view, int newProgress) {
    //    super.onProgressChanged(view, newProgress);
    //    if (newProgress == 100) {
    //      view.postDelayed(new Runnable() {
    //        @Override public void run() {
    //          view.setVisibility(View.VISIBLE);
    //          hideProgress();
    //        }
    //      }, 300);
    //    }
    //  }
    //});
  }

  @Override public void showData(ZhuanlanPost data) {
    Log.i(TAG, data.getContent());
    String css =
        "<link rel=\"stylesheet\" href=\"file:///android_asset/css/master.css\" type=\"text/css\">";
    String html = "<html><head>" + css + "</head><body>" + data.getContent() + "</body></html>";
    //html = html.replace("<div class=\"img-place-holder\">", "");
    mWebContainer.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);

    //String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/master.css\" type=\"text/css\">";
    //String html = "<!DOCTYPE html>\n"
    //    + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
    //    + "<head>\n"
    //    + "\t<meta charset=\"utf-8\" />\n</head>\n"
    //    + css
    //    + "\n<body>"
    //    + data.getContent()
    //    + "</body>\n</html>";
    //mWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
  }

  @Override public void showProgress() {
    mProgress.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    mProgress.setVisibility(View.GONE);
  }

  @Override public void showLoadFailMsg() {

  }
}

package com.oeasy9999.zhihudemo.ui.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.mvp.presenter.MainPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.MainPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.MainView;
import com.oeasy9999.zhihudemo.ui.fragment.RemenFragment;
import com.oeasy9999.zhihudemo.ui.fragment.RibaoFragment;
import com.oeasy9999.zhihudemo.ui.fragment.TabsFragment;
import com.oeasy9999.zhihudemo.ui.fragment.ZhutiFragment;

public class MainActivity extends BaseActivity
    implements MainView, NavigationView.OnNavigationItemSelectedListener {

  @Bind(R.id.toolbar) Toolbar mToolbar;
  @Bind(R.id.nav_view) NavigationView mNavView;
  @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;

  public static final String TAG = "MainActivity";
  private MainPresenter mMainPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initNavigationView();
    mMainPresenter = new MainPresenterImpl(this);
    switchToRibao();
  }

  private void initNavigationView() {
    ActionBarDrawerToggle actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();

    mNavView.setItemIconTintList(null);//设置菜单图标恢复本来的颜色
    mNavView.setItemTextColor(null);
    mNavView.setItemTextColor(wordCheckedStateColor(R.color.white, R.color.textOrange));
    mNavView.setNavigationItemSelectedListener(this);
    mNavView.getMenu().getItem(0).setChecked(true);

    ImageView navHeaderImage =
        ButterKnife.findById(mNavView.getHeaderView(0), R.id.image_nav_header);
    navHeaderImage.setImageResource(R.mipmap.head);
  }

  private ColorStateList wordCheckedStateColor(int white, int textOrange) {
    int[][] states = new int[][] {
        new int[] { -android.R.attr.state_checked }, new int[] { android.R.attr.state_checked }
    };
    int[] colors =
        new int[] { getResources().getColor(white), getResources().getColor(textOrange) };
    ColorStateList csl = new ColorStateList(states, colors);
    return csl;
  }

  @Override public boolean onNavigationItemSelected(MenuItem item) {
    mMainPresenter.switchNavigation(item.getItemId());
    item.setChecked(true);
    mDrawerLayout.closeDrawers();
    //mDrawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void onBackPressed() {
    if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
      mDrawerLayout.closeDrawer(GravityCompat.START);
    }
    super.onBackPressed();
  }

  @Override public void switchToRibao() {
    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new RibaoFragment()).commit();
    mToolbar.setTitle(R.string.ribao);
  }

  @Override public void switchToRemen() {
    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new RemenFragment()).commit();
    mToolbar.setTitle(R.string.remen);
  }

  @Override public void switchToZhuti() {
    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new ZhutiFragment()).commit();
    mToolbar.setTitle(R.string.zhuti);
  }

  @Override public void switchToZhuanlan() {
    getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new TabsFragment()).commit();
    mToolbar.setTitle(R.string.zhuanlan);
  }
}

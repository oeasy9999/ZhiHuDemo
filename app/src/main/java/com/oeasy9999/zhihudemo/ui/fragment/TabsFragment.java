package com.oeasy9999.zhihudemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.ZhiHuApp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/8/24.
 */
public class TabsFragment extends BaseFragment {

  @Bind(R.id.tabs) TabLayout mTabs;
  @Bind(R.id.pager) ViewPager mPager;
  private TabPagerAdapter adapter;
  private List<Fragment> fragments = new ArrayList<>();
  //private ZhutiList zhutiList = null;
  //private IPresenter zhutiPresenter;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_tab, container, false);
    ButterKnife.bind(this, view);
    initViews();
    return view;
  }

  private void initViews() {
    adapter = new TabPagerAdapter(getChildFragmentManager());
    initFragments();
    mPager.setOffscreenPageLimit(7);
    mPager.setAdapter(adapter);
    //mPager.setCurrentItem(1);
    mTabs.setupWithViewPager(mPager);
    //mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    //  @Override public void onTabSelected(TabLayout.Tab tab) {
    //    mPager.setCurrentItem(tab.getPosition());
    //  }
    //
    //  @Override public void onTabUnselected(TabLayout.Tab tab) {
    //
    //  }
    //
    //  @Override public void onTabReselected(TabLayout.Tab tab) {
    //
    //  }
    //});
  }

  private void initFragments() {
    List<String> mTitles;
    String[] titles = new String[] {
        ZhiHuApp.appContext().getResources().getString(R.string.tab_tech),
        ZhiHuApp.appContext().getResources().getString(R.string.tab_photography),
        ZhiHuApp.appContext().getResources().getString(R.string.tab_music_film),
        ZhiHuApp.appContext().getResources().getString(R.string.tab_life_talks),
        ZhiHuApp.appContext().getResources().getString(R.string.tab_psychology),
        ZhiHuApp.appContext().getResources().getString(R.string.tab_financial),
        ZhiHuApp.appContext().getResources().getString(R.string.tab_zhihu)
    };
    mTitles = Arrays.asList(titles);

    //fragments.add(new TechFragment());
    //fragments.add(new TechFragment());
    //fragments.add(new TechFragment());
    //fragments.add(new TechFragment());
    //fragments.add(new TechFragment());
    //fragments.add(new TechFragment());
    //fragments.add(new TechFragment());
    fragments.add(ZhuanlanFragment.newInstance(ZhuanlanFragment.TYPE_HULIANWANG));
    fragments.add(ZhuanlanFragment.newInstance(ZhuanlanFragment.TYPE_SHEYING));
    fragments.add(ZhuanlanFragment.newInstance(ZhuanlanFragment.TYPE_YINGSHI));
    fragments.add(ZhuanlanFragment.newInstance(ZhuanlanFragment.TYPE_ZATAN));
    fragments.add(ZhuanlanFragment.newInstance(ZhuanlanFragment.TYPE_XINLI));
    fragments.add(ZhuanlanFragment.newInstance(ZhuanlanFragment.TYPE_JINRONG));
    fragments.add(ZhuanlanFragment.newInstance(ZhuanlanFragment.TYPE_CHENGXUYUAN));

    adapter.setFragments(fragments, mTitles);
  }

  public static class TabPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;

    public TabPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    public void setFragments(List<Fragment> fragments, List<String> titles) {
      this.fragments = fragments;
      this.titles = titles;
    }

    @Override public Fragment getItem(int position) {
      return fragments.get(position);
    }

    @Override public int getCount() {
      return fragments.size();
    }

    @Override public CharSequence getPageTitle(int position) {
      return titles.get(position);
    }

    @Override public float getPageWidth(int position) {
      return super.getPageWidth(position);
    }
  }
}

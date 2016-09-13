package com.oeasy9999.zhihudemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.oeasy9999.zhihudemo.service.API;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.model.entity.Story;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.mvp.presenter.RibaoPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.RibaoPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;
import com.oeasy9999.zhihudemo.ui.activity.NewsDetailActivity;
import com.oeasy9999.zhihudemo.ui.adapter.RibaoAdapter;
import com.oeasy9999.zhihudemo.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/8/25.
 */
public class RibaoFragment extends BaseFragment
    implements IView<Ribao>, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;

  private Ribao mData;
  private List<Story> mStories = new ArrayList<>();
  private RibaoPresenter mRibaoPresenter;
  private RibaoAdapter adapter;
  private LinearLayoutManager layoutManager;
  private ConvenientBanner banner;
  private boolean first = true;

  public static RibaoFragment newInstance() {
    RibaoFragment fragment = new RibaoFragment();
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mRibaoPresenter = new RibaoPresenterImpl(this);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ribao, null);
    ButterKnife.bind(this, view);
    mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark,
        R.color.colorAccent, R.color.textOrange);
    mSwipeRefreshWidget.setOnRefreshListener(this);

    mRecyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    adapter = new RibaoAdapter(this, getActivity());
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
          onListScrolled();
        }
      }
    });
    onRefresh();
    return view;
  }

  private void onListScrolled() {
    //initBanner();
    int firstPosition = layoutManager.findFirstVisibleItemPosition();
    int lastPosition = layoutManager.findLastVisibleItemPosition();
    if (lastPosition + 1 == adapter.getItemCount() && adapter.mShowFooter) {
      mRibaoPresenter.loadLaest(API.TYPE_BEFORE);
    }
  }

  private void initBanner() {
    if (null == banner) {
      if (mRecyclerView.getChildCount() != 0 && layoutManager.findFirstVisibleItemPosition() == 0) {
        banner = (ConvenientBanner) layoutManager.findViewByPosition(0);
        banner.setScrollDuration(1000);
        banner.startTurning(5000);
      }
    }
  }

  @Override public void onRefresh() {
    if (mData != null) mData = null;//整个过程会多次调用此方法，不清除会造成数据累加，显示重复
    if (mStories != null) mStories.clear();
    mRibaoPresenter.loadLaest(API.TYPE_LATEST);
  }

  @Override public void onDestroyView() {
    if (banner != null) {
      banner.stopTurning();
    }
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void showProgress() {
    mSwipeRefreshWidget.setRefreshing(true);
    adapter.mShowFooter = false;
  }

  @Override public void showData(Ribao ribao) {
    Story tip = new Story(1, TimeUtils.convertDate(ribao.getData()));
    List<Story> stories = ribao.getStories();
    stories.add(0, tip);
    mStories.addAll(stories);
    adapter.setStories(mStories);
    if (mData == null) mData = ribao;
    adapter.mShowFooter = true;
    if (first) {
      adapter.setData(ribao);
      mRecyclerView.setAdapter(adapter);
      first = false;
    } else {
      adapter.notifyDataSetChanged();
    }
  }

  @Override public void hideProgress() {
    mSwipeRefreshWidget.setRefreshing(false);
  }

  @Override public void showLoadFailMsg() {
    Toast.makeText(getActivity(), "加载失败！", Toast.LENGTH_SHORT).show();
  }

  @Override public void onItemClick(RecyclerView.ViewHolder holder) {
    if (holder instanceof RibaoAdapter.ItemViewHolder) {
      RibaoAdapter.ItemViewHolder itemViewHolder = (RibaoAdapter.ItemViewHolder) holder;
      Story story = itemViewHolder.story;
      Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
      intent.putExtra("story", story);
      ActivityOptionsCompat optionsCompat =
          ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
              itemViewHolder.mStoryImg, getString(R.string.shared_img));
      ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
    }
  }
}

package com.oeasy9999.zhihudemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.HotNews;
import com.oeasy9999.zhihudemo.model.entity.Remen;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.mvp.presenter.IPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.RemenPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;
import com.oeasy9999.zhihudemo.ui.activity.NewsDetailActivity;
import com.oeasy9999.zhihudemo.ui.adapter.RemenAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class RemenFragment extends BaseFragment implements IView<Remen>, SwipeRefreshLayout.OnRefreshListener,
    OnItemClickListener {

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;

  private LinearLayoutManager layoutManager;
  private IPresenter mRemenPresenter;
  private List<HotNews> hotNewses = new ArrayList<>();
  private RemenAdapter remenAdapter;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mRemenPresenter = new RemenPresenterImpl(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_remen, null);
    ButterKnife.bind(this, view);

    mSwipeRefreshWidget.setOnRefreshListener(this);
    mRecyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    remenAdapter = new RemenAdapter(getActivity(), hotNewses, this);
    mRecyclerView.setAdapter(remenAdapter);

    onRefresh();
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onRefresh() {
    mRemenPresenter.loadData();
  }

  @Override public void showData(Remen remen) {
    if (remen == null || remen.getRecent().size() == 0) return;
    hotNewses.addAll(remen.getRecent());
    remenAdapter.notifyDataSetChanged();
  }

  @Override public void showProgress() {
    mSwipeRefreshWidget.setRefreshing(true);
  }

  @Override public void hideProgress() {
    mSwipeRefreshWidget.setRefreshing(false);
  }

  @Override public void showLoadFailMsg() {
    Toast.makeText(getActivity(), "加载失败！", Toast.LENGTH_SHORT).show();
  }

  @Override public void onItemClick(RecyclerView.ViewHolder holder) {
    if (holder instanceof RemenAdapter.ItemViewHolder) {
      RemenAdapter.ItemViewHolder itemViewHolder = (RemenAdapter.ItemViewHolder) holder;
      HotNews hotNews = itemViewHolder.hotNews;
      Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
      intent.putExtra("story", hotNews);
      startActivity(intent);
    }
  }
}

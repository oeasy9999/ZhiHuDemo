package com.oeasy9999.zhihudemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.HotNews;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.mvp.presenter.RemenPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.RemenPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.RemenView;
import com.oeasy9999.zhihudemo.ui.activity.NewsDetailActivity;
import com.oeasy9999.zhihudemo.ui.adapter.RemenAdapter;
import java.util.ArrayList;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class RemenFragment extends BaseFragment implements RemenView, SwipeRefreshLayout.OnRefreshListener,
    OnItemClickListener {

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;

  private LinearLayoutManager layoutManager;
  private RemenPresenter mRemenPresenter;

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

    onRefresh();
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onRefresh() {

    mRemenPresenter.loadHotNews();
  }

  @Override public void showRemen(ArrayList<HotNews> hotNewses) {
    Log.i("哈哈哈", hotNewses.size()+"");
    RemenAdapter remenAdapter = new RemenAdapter(getActivity(), hotNewses, this);
    mRecyclerView.setAdapter(remenAdapter);
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

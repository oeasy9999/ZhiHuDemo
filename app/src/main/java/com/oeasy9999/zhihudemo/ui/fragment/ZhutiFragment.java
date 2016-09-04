package com.oeasy9999.zhihudemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.Zhuti;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhutiPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhutiPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.ZhutiView;
import com.oeasy9999.zhihudemo.ui.adapter.ZhutiListAdapter;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiFragment extends BaseFragment implements ZhutiView, SwipeRefreshLayout.OnRefreshListener{

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;
  private ZhutiPresenter zhutiPresenter;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    zhutiPresenter = new ZhutiPresenterImpl(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ribao, null);
    ButterKnife.bind(this, view);

    mSwipeRefreshWidget.setOnRefreshListener(this);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    onRefresh();
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onRefresh() {
    zhutiPresenter.loadZhutiList();
  }

  @Override public void showZhutiList(List<Zhuti> zhutiList) {
    ZhutiListAdapter adapter = new ZhutiListAdapter(getActivity(), zhutiList);
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
    Toast.makeText(getActivity(), "加载失败！", Toast.LENGTH_SHORT).show();
  }
}

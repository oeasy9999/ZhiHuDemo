package com.oeasy9999.zhihudemo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.ZhutiList;
import com.oeasy9999.zhihudemo.mvp.presenter.IPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhutiPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;
import com.oeasy9999.zhihudemo.ui.adapter.ZhutiListAdapter;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiFragment extends BaseFragment implements IView<ZhutiList>, SwipeRefreshLayout.OnRefreshListener{

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;
  private IPresenter zhutiPresenter;

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
    //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    onRefresh();
    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onRefresh() {
    zhutiPresenter.loadData();
  }

  @Override public void showData(ZhutiList zhutiList) {
    //Log.i("ZhutiFragment哈哈哈", zhutiList.size()+"");
    ZhutiListAdapter adapter = new ZhutiListAdapter(getActivity(), zhutiList.getZhutis());
    mRecyclerView.setAdapter(adapter);
    adapter.notifyDataSetChanged();
  }

  @Override public void showProgress() {
    mSwipeRefreshWidget.setRefreshing(true);
  }

  @Override public void hideProgress() {
    Log.i("ZhutiFragment嘻嘻嘻", "隐藏");
    mSwipeRefreshWidget.setRefreshing(false);
  }

  @Override public void showLoadFailMsg() {
    Toast.makeText(getActivity(), "加载失败！", Toast.LENGTH_SHORT).show();
  }
}

package com.oeasy9999.zhihudemo.ui.fragment;

import android.content.Intent;
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
import com.oeasy9999.zhihudemo.model.entity.Zhuti;
import com.oeasy9999.zhihudemo.model.entity.ZhutiList;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.mvp.presenter.IPresenter;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhutiPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.IView;
import com.oeasy9999.zhihudemo.ui.activity.ThemeActivity;
import com.oeasy9999.zhihudemo.ui.adapter.ZhutiListAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiFragment extends BaseFragment
    implements IView<ZhutiList>, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;

  private static final String TAG = "ZhutiFragment测试";
  private List<Zhuti> mZhutiList = new ArrayList<>();
  private IPresenter zhutiPresenter;
  private ZhutiListAdapter adapter;

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
    adapter = new ZhutiListAdapter(getActivity(), mZhutiList, this);
    mRecyclerView.setAdapter(adapter);

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
    mZhutiList.addAll(zhutiList.getZhutis());
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

  @Override public void onItemClick(RecyclerView.ViewHolder holder) {
    if (holder instanceof ZhutiListAdapter.ItemViewHolder) {
      ZhutiListAdapter.ItemViewHolder itemViewHolder = (ZhutiListAdapter.ItemViewHolder) holder;
      Zhuti zhuti = itemViewHolder.zhuti;
      if (zhuti == null) Log.i(TAG, "空三孔空");
      Log.i(TAG, zhuti.getName()+"-->"+zhuti.getId());
      //Bundle bundle = new Bundle();
      //bundle.putSerializable("theme", zhuti);
      //getActivity().getSupportFragmentManager()
      //    .beginTransaction()
      //    .replace(R.id.content_main, ZhuanlanFragment.newInstance(bundle))
      //    .addToBackStack(null)
      //    .commit();
      Intent intent = new Intent(getActivity(), ThemeActivity.class);
      intent.putExtra("theme", zhuti);
      startActivity(intent);
    }
  }
}

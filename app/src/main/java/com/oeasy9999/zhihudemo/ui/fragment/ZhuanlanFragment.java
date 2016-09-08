package com.oeasy9999.zhihudemo.ui.fragment;

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
import butterknife.Bind;
import butterknife.ButterKnife;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.Zhuanlan;
import com.oeasy9999.zhihudemo.mvp.presenter.ZhuanlanPresenterImpl;
import com.oeasy9999.zhihudemo.mvp.view.ZhuanlanView;
import com.oeasy9999.zhihudemo.ui.activity.MainActivity;
import com.oeasy9999.zhihudemo.ui.adapter.ZhuanlanAdapter;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/5.
 */
public class ZhuanlanFragment extends BaseFragment
    implements ZhuanlanView, SwipeRefreshLayout.OnRefreshListener {
  public static final int TYPE_HULIANWANG = 0;
  public static final int TYPE_SHEYING = 1;
  public static final int TYPE_YINGSHI = 2;
  public static final int TYPE_ZATAN = 3;
  public static final int TYPE_XINLI = 4;
  public static final int TYPE_JINRONG = 5;
  public static final int TYPE_CHENGXUYUAN = 6;

  private static final String TAG = "ZhuanlanFragment测试";
  private ZhuanlanPresenterImpl zhuanlanPresenter;
  //List<Zhuanlan> zhuanlans = new ArrayList<>();
  private int type;
  private String[] ids;

  @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
  @Bind(R.id.swipe_refresh_widget) SwipeRefreshLayout mSwipeRefreshWidget;
  private LinearLayoutManager layoutManager;

  public static ZhuanlanFragment newInstance(int type) {
    Bundle args = new Bundle();
    args.putInt("type", type);
    ZhuanlanFragment fragment = new ZhuanlanFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    zhuanlanPresenter = new ZhuanlanPresenterImpl(this);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_recycler, container, false);
    ButterKnife.bind(this, view);

    mSwipeRefreshWidget.setOnRefreshListener(this);
    mRecyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    type = getArguments().getInt("type");
    Log.i(TAG, type + "号-->fragment");
    onRefresh();
    return view;
  }

  @Override public void onRefresh() {
    //if (zhuanlans.size() > 0) zhuanlans.clear();
    ids = switchId(type);
    zhuanlanPresenter.loadData(ids);
  }

  private String[] switchId(int type) {
    String[] items;
    switch (type) {
      case TYPE_HULIANWANG:
        items = getActivity().getResources().getStringArray(R.array.tech_ids);
        break;
      case TYPE_SHEYING:
        items = getActivity().getResources().getStringArray(R.array.photography_ids);
        break;
      case TYPE_YINGSHI:
        items = getActivity().getResources().getStringArray(R.array.music_film_ids);
        break;
      case TYPE_ZATAN:
        items = getActivity().getResources().getStringArray(R.array.life_talks_ids);
        break;
      case TYPE_XINLI:
        items = getActivity().getResources().getStringArray(R.array.psychology_ids);
        break;
      case TYPE_JINRONG:
        items = getActivity().getResources().getStringArray(R.array.financial_ids);
        break;
      default:
        items = getActivity().getResources().getStringArray(R.array.zhihu_ids);
    }
    return items;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void showData(List<Zhuanlan> data) {
    ZhuanlanAdapter adapter = new ZhuanlanAdapter((MainActivity) getActivity(), data);
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

  }
}

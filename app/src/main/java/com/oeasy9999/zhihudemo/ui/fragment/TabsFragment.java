package com.oeasy9999.zhihudemo.ui.fragment;

/**
 * Created by oeasy9999 on 2016/8/24.
 */
public class TabsFragment extends BaseFragment /*implements IView<ZhutiList>*/ {

  //@Bind(R.id.tabs) TabLayout mTabs;
  //@Bind(R.id.pager) ViewPager mPager;
  //private TabPagerAdapter adapter;
  //private ZhutiList zhutiList = null;
  //private IPresenter zhutiPresenter;
  //
  //@Override public void onCreate(@Nullable Bundle savedInstanceState) {
  //  super.onCreate(savedInstanceState);
  //  zhutiPresenter = new ZhutiPresenterImpl(this);
  //}
  //
  //@Nullable @Override
  //public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
  //    @Nullable Bundle savedInstanceState) {
  //  View view = inflater.inflate(R.layout.fragment_tab, container, false);
  //  ButterKnife.bind(this, view);
  //  //initViews();
  //  zhutiPresenter.loadData();
  //  Toast.makeText(getActivity(), zhutiList.getZhutis().size()+"", Toast.LENGTH_SHORT).show();
  //  //initFragments();
  //  return view;
  //}
  //
  //private void initViews() {
  //  adapter = new TabPagerAdapter(getChildFragmentManager());
  //  //initFragments();
  //  mPager.setAdapter(adapter);
  //  mPager.setCurrentItem(0);
  //  mTabs.setupWithViewPager(mPager);
  //  mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
  //    @Override public void onTabSelected(TabLayout.Tab tab) {
  //      mPager.setCurrentItem(tab.getPosition());
  //    }
  //
  //    @Override public void onTabUnselected(TabLayout.Tab tab) {
  //
  //    }
  //
  //    @Override public void onTabReselected(TabLayout.Tab tab) {
  //
  //    }
  //  });
  //}
  //
  //private void initFragments() {
  //  //Zhuti zhuti1 = new Zhuti("电视", 1);
  //  //Zhuti zhuti2 = new Zhuti("电影不无聊", 2);
  //  //Zhuti zhuti3 = new Zhuti("电影有的聊", 3);
  //  List<Zhuti> zhutis = zhutiList.getZhutis();
  //  //Map<String, Intent> map = new HashMap<>();
  //  List<String> mTitles = new ArrayList<>();
  //  List<Fragment> mFragments = new ArrayList<>();
  //  Log.i("TabsFragment111111", zhutis.size()+"");
  //  for (int i = 0; i < zhutis.size(); i++) {
  //    String name = zhutis.get(i).getName();
  //    int id = zhutis.get(i).getId();
  //    Log.i("233333", id + "-->" + name);
  //    mTitles.add(name);
  //    mFragments.add(NewsFragment.newInstance(id));
  //  }
  //  adapter.setFragments(mFragments, mTitles);
  //  //for (int i = 0; i < mTitles.size(); i++) {
  //  //  mFragments.add(NewsFragment.newInstance());
  //  //}
  //}
  //
  //@Override public void onDestroyView() {
  //  super.onDestroyView();
  //  ButterKnife.unbind(this);
  //}
  //
  //@Override public void showData(ZhutiList data) {
  //  Log.i("NewsFragment2222", data.getZhutis().size()+"");
  //  Toast.makeText(getActivity(), zhutiList.getZhutis().size()+"", Toast.LENGTH_SHORT).show();
  //  this.zhutiList = data;
  //}
  //
  //@Override public void showProgress() {
  //
  //}
  //
  //@Override public void hideProgress() {
  //
  //}
  //
  //@Override public void showLoadFailMsg() {
  //
  //}
  //
  //public static class TabPagerAdapter extends FragmentPagerAdapter {
  //
  //  private List<Fragment> fragments;
  //  private List<String> titles;
  //
  //  public TabPagerAdapter(FragmentManager fm) {
  //    super(fm);
  //  }
  //
  //  public void setFragments(List<Fragment> fragments, List<String> titles) {
  //    this.fragments = fragments;
  //    this.titles = titles;
  //  }
  //
  //  @Override public Fragment getItem(int position) {
  //    return fragments.get(position);
  //  }
  //
  //  @Override public int getCount() {
  //    return fragments.size();
  //  }
  //
  //  @Override public CharSequence getPageTitle(int position) {
  //    return titles.get(position);
  //  }
  //
  //  @Override public float getPageWidth(int position) {
  //    return super.getPageWidth(position);
  //  }
  //}
}

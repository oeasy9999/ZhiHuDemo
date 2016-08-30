package com.oeasy9999.zhihudemo.mvp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.TopStory;

/**
 * Created by oeasy9999 on 2016/8/29.
 */
public class BannerView implements Holder<TopStory> {

  @Bind(R.id.banner_img) ImageView bannerImg;
  @Bind(R.id.banner_title) TextView bannerTitle;
  private View view;

  @Override public View createView(Context context) {
    view = LayoutInflater.from(context).inflate(R.layout.list_item_banner, null);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void UpdateUI(Context context, int position, TopStory data) {
    Glide.with(context).load(data.getImage()).into(bannerImg);
    bannerTitle.setText(data.getTitle());
  }
}

package com.oeasy9999.zhihudemo.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.bigkoo.convenientbanner.holder.Holder;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.TopStory;
import com.oeasy9999.zhihudemo.ui.activity.MainActivity;
import com.oeasy9999.zhihudemo.ui.activity.NewsDetailActivity;
import com.oeasy9999.zhihudemo.utils.ImageUtils;

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

  @Override public void UpdateUI(final Context context, int position, final TopStory data) {
    ImageUtils.load(context, data.getImage(), bannerImg);
    bannerTitle.setText(data.getTitle());
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra("story", data);
        ActivityOptionsCompat optionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation((MainActivity) context,
                bannerImg, context.getString(R.string.shared_img));
        ActivityCompat.startActivity((MainActivity) context, intent, optionsCompat.toBundle());
      }
    });
  }
}

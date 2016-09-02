package com.oeasy9999.zhihudemo.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oeasy9999.zhihudemo.R;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class ImageUtils {
  public static void load(Context context, String url, ImageView view) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_menu_gallery)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .crossFade()
        .into(view);
  }

  public static void load(Context context, int resourceId, ImageView view) {
    Glide.with(context)
        .load(resourceId)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .crossFade()
        .into(view);
  }

  public static void loadWithPlaceholder(Context context, String url, int resourceId, ImageView view) {
    Glide.with(context)
        .load(url)
        .placeholder(resourceId)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view);
  }
}

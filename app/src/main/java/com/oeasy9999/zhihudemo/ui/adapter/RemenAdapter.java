package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.HotNews;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import java.util.ArrayList;

/**
 * Created by oeasy9999 on 2016/9/2.
 */
public class RemenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context mContext;
  private ArrayList<HotNews> hotNewses;
  private OnItemClickListener mListener;

  public RemenAdapter(Context context, ArrayList<HotNews> hotNewses, OnItemClickListener listener) {
    this.mContext = context;
    this.hotNewses = hotNewses;
    this.mListener = listener;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_remen, null);---->如果用这个，会出现CardView设置margin属性无效。
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_remen, parent, false);
    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

      itemViewHolder.hotNews = hotNewses.get(position);
      ImageUtils.load(mContext, itemViewHolder.hotNews.getThumbnail(), itemViewHolder.imgThumbnail);
      itemViewHolder.txtHotTitle.setText(itemViewHolder.hotNews.getTitle());
      itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (null != mListener) mListener.onItemClick(itemViewHolder);
        }
      });
    }
  }

  @Override public int getItemCount() {
    return hotNewses.size();
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgThumbnail;
    public TextView txtHotTitle;
    public HotNews hotNews;

    public ItemViewHolder(View itemView) {
      super(itemView);
      imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
      txtHotTitle = (TextView) itemView.findViewById(R.id.txt_hot_title);
    }
  }
}

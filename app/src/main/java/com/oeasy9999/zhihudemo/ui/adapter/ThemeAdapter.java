package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.Story;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/6.
 */
public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context context;
  private List<Story> stories;
  private OnItemClickListener listener;

  public ThemeAdapter(Context context, List<Story> stories, OnItemClickListener listener) {
    this.context = context;
    this.stories = stories;
    this.listener = listener;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ribao, parent, false);
    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.story = stories.get(position);
      if (itemViewHolder.story.getImages() == null) {
        itemViewHolder.mStoryImg.setVisibility(View.GONE);
      } else {
        itemViewHolder.mStoryImg.setVisibility(View.VISIBLE);
        ImageUtils.load(context, itemViewHolder.story.getImages()[0], itemViewHolder.mStoryImg);
      }
      itemViewHolder.mStoryTitle.setText(itemViewHolder.story.getTitle());
      itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          listener.onItemClick(itemViewHolder);
        }
      });
    }
  }

  @Override public int getItemCount() {
    return stories.size();
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView mStoryTitle;
    public ImageView mStoryImg;
    public Story story;

    public ItemViewHolder(View itemView) {
      super(itemView);
      mStoryTitle = (TextView) itemView.findViewById(R.id.story_title);
      mStoryImg = (ImageView) itemView.findViewById(R.id.story_img);
    }
  }
}

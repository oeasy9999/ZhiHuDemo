package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanPost;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import com.oeasy9999.zhihudemo.utils.TimeUtils;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/8.
 */
public class ZhuanlanPostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_ITEM = 1;
  private static final int TYPE_FOOTER = 2;
  private Context context;
  private List<ZhuanlanPost> zhuanlanPosts;
  public boolean showFooter = true;

  public ZhuanlanPostsAdapter(Context context, List<ZhuanlanPost> zhuanlanPosts) {
    this.context = context;
    this.zhuanlanPosts = zhuanlanPosts;
  }

  public void setZhuanlanPosts(List<ZhuanlanPost> zhuanlanPosts) {
    this.zhuanlanPosts = zhuanlanPosts;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    if (viewType == TYPE_FOOTER) {
      View view = layoutInflater.inflate(R.layout.list_item_footer, parent, false);
      return new FooterViewHolder(view);
    } else {
      View view = layoutInflater.inflate(R.layout.list_item_zhuanlan_post, parent, false);
      return new ItemViewHolder(view);
    }
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.zhuanlanPost = zhuanlanPosts.get(position);
      if (itemViewHolder.zhuanlanPost.getTitleImage() == null || itemViewHolder.zhuanlanPost.getTitleImage().equals("")) {
        itemViewHolder.imgZhuanlanPost.setVisibility(View.GONE);
      } else {
        itemViewHolder.imgZhuanlanPost.setVisibility(View.VISIBLE);
        ImageUtils.load(context, itemViewHolder.zhuanlanPost.getTitleImage(), itemViewHolder.imgZhuanlanPost);
      }
      itemViewHolder.txtTitle.setText(itemViewHolder.zhuanlanPost.getTitle());
      itemViewHolder.txtName.setText(String.valueOf(itemViewHolder.zhuanlanPost.getAuthorName()));
      itemViewHolder.txtPublishTime.setText(TimeUtils.convertPublishTime(itemViewHolder.zhuanlanPost.getPublishedTime()));
      itemViewHolder.txtPopularity.setText(String.valueOf(itemViewHolder.zhuanlanPost.getLikesCount()));
      itemViewHolder.txtCommentNum.setText(String.valueOf(itemViewHolder.zhuanlanPost.getCommentsCount()));
    }
  }

  @Override public int getItemViewType(int position) {
    if (showFooter && position + 1 == getItemCount()) return TYPE_FOOTER;
    return TYPE_ITEM;
  }

  @Override public int getItemCount() {
    int footer = showFooter ? 1 : 0;
    return zhuanlanPosts.size() + footer;
  }

  /////////////////////////////////////////////////////////////////////////////////////////

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgZhuanlanPost;
    private TextView txtTitle;
    private TextView txtName;
    private TextView txtPublishTime;
    private TextView txtPopularity;
    private TextView txtCommentNum;
    public ZhuanlanPost zhuanlanPost;

    public ItemViewHolder(View itemView) {
      super(itemView);
      imgZhuanlanPost = (ImageView) itemView.findViewById(R.id.img_zhuanlan_post);
      txtTitle = (TextView) itemView.findViewById(R.id.txt_zhuanlan_post_title);
      txtName = (TextView) itemView.findViewById(R.id.txt_zhuanlan_post_name);
      txtPublishTime = (TextView) itemView.findViewById(R.id.txt_zhuanlan_post_time);
      txtPopularity = (TextView) itemView.findViewById(R.id.txt_zhuanlan_post_popularity);
      txtCommentNum = (TextView) itemView.findViewById(R.id.txt_zhuanlan_post_commentnum);
    }
  }

  private class FooterViewHolder extends RecyclerView.ViewHolder {
    public FooterViewHolder(View view) {
      super(view);
    }
  }
}

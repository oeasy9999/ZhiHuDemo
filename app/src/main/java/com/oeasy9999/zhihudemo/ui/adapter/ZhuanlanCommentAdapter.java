package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.ZhuanlanComment;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import com.oeasy9999.zhihudemo.utils.TimeUtils;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/12.
 */
public class ZhuanlanCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<ZhuanlanComment> zhuanlanComments;
  private Context context;

  public ZhuanlanCommentAdapter(Context context, List<ZhuanlanComment> zhuanlanComments) {
    this.context = context;
    this.zhuanlanComments = zhuanlanComments;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_zhuanlan_comment, parent, false);
    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.zhuanlanComment = zhuanlanComments.get(position);
      if (itemViewHolder.zhuanlanComment.getAvatarUrl() != null) {
        ImageUtils.load(context, itemViewHolder.zhuanlanComment.getAvatarUrl(), itemViewHolder.imgAvatar);
      }
      itemViewHolder.txtCommentName.setText(itemViewHolder.zhuanlanComment.getAuthorName());
      itemViewHolder.txtCommentContent.setText(itemViewHolder.zhuanlanComment.getContent());
      itemViewHolder.txtCommentPublishTime.setText(TimeUtils.convertCreatedTime(itemViewHolder.zhuanlanComment.getCreatedTime()));
      itemViewHolder.txtCommentLikeNum.setText(String.valueOf(itemViewHolder.zhuanlanComment.getLikesCount()));
    }
  }

  @Override public int getItemCount() {
    return zhuanlanComments.size();
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgAvatar;
    private TextView txtCommentName;
    private TextView txtCommentContent;
    private TextView txtCommentPublishTime;
    private TextView txtCommentLikeNum;
    public ZhuanlanComment zhuanlanComment;

    public ItemViewHolder(View itemView) {
      super(itemView);
      imgAvatar = (ImageView) itemView.findViewById(R.id.img_avatar);
      txtCommentName = (TextView) itemView.findViewById(R.id.txt_comment_name);
      txtCommentContent = (TextView) itemView.findViewById(R.id.txt_comment_content);
      txtCommentPublishTime = (TextView) itemView.findViewById(R.id.txt_comment_publishtime);
      txtCommentLikeNum = (TextView) itemView.findViewById(R.id.txt_comment_likenum);
    }
  }
}

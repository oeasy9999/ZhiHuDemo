package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.ZhiHuApp;
import com.oeasy9999.zhihudemo.model.entity.Zhuanlan;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import com.oeasy9999.zhihudemo.widget.CircleImageView;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/7.
 */
public class ZhuanlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final String TAG = "ZhuanlanAdapter测试";
  private Context context;
  private List<Zhuanlan> zhuanlanList;
  private OnItemClickListener listener;

  public ZhuanlanAdapter(Context context, List<Zhuanlan> zhuanlans, OnItemClickListener listener) {
    this.context = context;
    this.zhuanlanList = zhuanlans;
    this.listener = listener;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_zhuanlan, parent, false);
    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.zhuanlan = zhuanlanList.get(position);
      ImageUtils.loadCircleImage(context, itemViewHolder.zhuanlan.getAvatarUrl(), itemViewHolder.imgavatar);
      itemViewHolder.txtName.setText(itemViewHolder.zhuanlan.getName());
      String strFunsCount = ZhiHuApp.appContext()
          .getResources()
          .getString(R.string.popularity_num, itemViewHolder.zhuanlan.getFollowerCount());
      itemViewHolder.txtFunsCount.setText(Html.fromHtml(strFunsCount));
      String strCommentCount = ZhiHuApp.appContext()
          .getResources()
          .getString(R.string.comment_num, itemViewHolder.zhuanlan.getPostCount());
      itemViewHolder.txtArticleCount.setText(Html.fromHtml(strCommentCount));
      itemViewHolder.txtDesc.setText(itemViewHolder.zhuanlan.getDescription());
      itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (null != listener) listener.onItemClick(itemViewHolder);
        }
      });
    }
  }

  @Override public int getItemCount() {
    return zhuanlanList.size();
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView imgavatar;
    private TextView txtName;
    private TextView txtFunsCount;
    private TextView txtArticleCount;
    private TextView txtDesc;
    public Zhuanlan zhuanlan;

    public ItemViewHolder(View itemView) {
      super(itemView);
      imgavatar = (CircleImageView) itemView.findViewById(R.id.avatar);
      txtName = (TextView) itemView.findViewById(R.id.txt_zhuanlan_name);
      txtFunsCount = (TextView) itemView.findViewById(R.id.txt_fans_count);
      txtArticleCount = (TextView) itemView.findViewById(R.id.txt_article_count);
      txtDesc = (TextView) itemView.findViewById(R.id.txt_zhuanlan_desc);
    }
  }
}

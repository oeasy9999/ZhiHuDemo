package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.ZhiHuApp;
import com.oeasy9999.zhihudemo.model.entity.Zhuanlan;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/7.
 */
public class ZhuanlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final String TAG = "ZhuanlanAdapter测试";
  private Context context;
  private List<Zhuanlan> zhuanlanList;

  public ZhuanlanAdapter(Context context, List<Zhuanlan> zhuanlans) {
    this.context = context;
    this.zhuanlanList = zhuanlans;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_zhuanlan, parent, false);
    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    //Log.i(TAG, "------------>");
    if (holder instanceof ItemViewHolder) {
      ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      //Log.i(TAG, zhuanlanList.get(position).getName());
      itemViewHolder.zhuanlan = zhuanlanList.get(position);
      //Log.i(TAG, itemViewHolder.zhuanlan.getName());
      ImageUtils.load(context, itemViewHolder.zhuanlan.getAvatarUrl(), itemViewHolder.imgavatar);
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
    }
  }

  @Override public int getItemCount() {
    return zhuanlanList.size();
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgavatar;
    private TextView txtName;
    private TextView txtFunsCount;
    private TextView txtArticleCount;
    private TextView txtDesc;
    public Zhuanlan zhuanlan;

    public ItemViewHolder(View itemView) {
      super(itemView);
      imgavatar = (ImageView) itemView.findViewById(R.id.avatar);
      txtName = (TextView) itemView.findViewById(R.id.txt_zhuanlan_name);
      txtFunsCount = (TextView) itemView.findViewById(R.id.txt_fans_count);
      txtArticleCount = (TextView) itemView.findViewById(R.id.txt_article_count);
      txtDesc = (TextView) itemView.findViewById(R.id.txt_zhuanlan_desc);
    }
  }
}

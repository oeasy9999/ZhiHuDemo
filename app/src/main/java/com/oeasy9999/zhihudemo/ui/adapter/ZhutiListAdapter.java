package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.Zhuti;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.utils.ImageUtils;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/9/3.
 */
public class ZhutiListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private Context mContext;
  private List<Zhuti> zhutiList;
  private OnItemClickListener mListener;

  public ZhutiListAdapter(Context context, List<Zhuti> zhutiList, OnItemClickListener listener) {
    this.mContext = context;
    this.zhutiList = zhutiList;
    this.mListener = listener;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_zhuti_list, parent, false);
    return new ItemViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.zhuti = zhutiList.get(position);
      ImageUtils.load(mContext, itemViewHolder.zhuti.getThumbnail(), itemViewHolder.imgThumbnail);
      itemViewHolder.txtName.setText(itemViewHolder.zhuti.getName());
      itemViewHolder.txtDesc.setText(itemViewHolder.zhuti.getDescription());
      itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (null != mListener) mListener.onItemClick(itemViewHolder);
        }
      });
    }
  }

  @Override public int getItemCount() {
    return zhutiList.size();
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder{

    public ImageView imgThumbnail;
    public TextView txtName;
    public TextView txtDesc;
    public Zhuti zhuti;

    public ItemViewHolder(View itemView) {
      super(itemView);
      imgThumbnail = (ImageView) itemView.findViewById(R.id.img_zhuti);
      txtName = (TextView) itemView.findViewById(R.id.txt_zhuti_name);
      txtDesc = (TextView) itemView.findViewById(R.id.txt_zhuti_desc);
    }
  }
}

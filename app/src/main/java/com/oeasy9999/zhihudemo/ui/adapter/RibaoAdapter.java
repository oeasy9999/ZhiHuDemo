package com.oeasy9999.zhihudemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.oeasy9999.zhihudemo.R;
import com.oeasy9999.zhihudemo.model.entity.Ribao;
import com.oeasy9999.zhihudemo.model.entity.Story;
import com.oeasy9999.zhihudemo.model.entity.TopStory;
import com.oeasy9999.zhihudemo.mvp.interf.OnItemClickListener;
import com.oeasy9999.zhihudemo.mvp.view.BannerView;
import java.util.List;

/**
 * Created by oeasy9999 on 2016/8/27.
 */
public class RibaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_BANNER = 0;
  private static final int TYPE_HEADER = 1;
  private static final int TYPE_ITEM = 2;
  private static final int TYPE_FOOTER = 3;

  private Context mContext;
  private Ribao ribao;
  private List<Story> stories;
  private List<TopStory> topStories;
  private ConvenientBanner<TopStory> banner;
  public boolean mShowFooter = true;
  private int footer;
  private OnItemClickListener mListener;

  public RibaoAdapter(OnItemClickListener listener, Context context) {
    this.mListener = listener;
    this.mContext = context;
  }

  public void setData(Ribao data) {
    Log.i("哈哈哈", data.getStories().size() + "");
    this.ribao = data;
    //this.stories = data.getStories();
    this.topStories = data.getTopStories();
    this.notifyDataSetChanged();
  }
  public void setStories(List<Story> stories) {
    this.stories = stories;
  }

  public void addList(){}

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    if (viewType == TYPE_BANNER) {
      View view = inflater.inflate(R.layout.fragment_banner, parent, false);
      return new BannerViewHolder(view);
    } else if (viewType == TYPE_HEADER) {

    } else if (viewType == TYPE_FOOTER) {
      View view = inflater.inflate(R.layout.list_item_footer, parent, false);
      return new FooterViewHolder(view);
    } else {
      View view = inflater.inflate(R.layout.list_item_ribao, parent, false);
      return new ItemViewHolder(view);
    }
    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ItemViewHolder) {
      final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
      itemViewHolder.story = stories.get(position - 1);
      if (itemViewHolder.story == null) return;
      Log.i("1111111",itemViewHolder.story.getType()+"-->"+itemViewHolder.story.getTitle());
      if(itemViewHolder.story.getType() == 1){
        itemViewHolder.mStoryImg.setVisibility(View.GONE);
        itemViewHolder.mStoryTitle.setGravity(Gravity.CENTER);
        itemViewHolder.mStoryTitle.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        itemViewHolder.mStoryTitle.setTextSize(12);
        itemViewHolder.itemView.setEnabled(false);
      } else {
        itemViewHolder.mStoryImg.setVisibility(View.VISIBLE);
        itemViewHolder.mStoryTitle.setGravity(Gravity.LEFT);
        itemViewHolder.mStoryTitle.setTextColor(mContext.getResources().getColor(R.color.primary_text));
        itemViewHolder.mStoryTitle.setTextSize(16);
        Glide.with(mContext).load(itemViewHolder.story.getImages()[0].toString()).into(itemViewHolder.mStoryImg);
        itemViewHolder.itemView.setEnabled(true);
      }
      itemViewHolder.mStoryTitle.setText(itemViewHolder.story.getTitle());
      itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (null != mListener) mListener.onItemClick(itemViewHolder);
        }
      });
    } else if (holder instanceof BannerViewHolder) {
      BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
      bannerViewHolder.banner.setPages(new CBViewHolderCreator<BannerView>() {
        @Override public BannerView createHolder() {
          return new BannerView();
        }
      }, topStories)
          .setPointViewVisible(true)
          .setPageIndicator(new int[] {
              R.drawable.shap_unselected_indicator, R.drawable.shape_selected_indicator
          })
          .startTurning(3000);
      banner = bannerViewHolder.banner;
    }
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return TYPE_BANNER;
    }
    if (!mShowFooter) return TYPE_ITEM;
    if (position + 1 == getItemCount()) return TYPE_FOOTER;
    return TYPE_ITEM;
  }

  @Override public int getItemCount() {
    footer = mShowFooter ? 1 : 0;
    return stories.size() + footer + 1;
  }

  /////////////////////////////////////////////////////////////////////////////////////////

  public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView mStoryTitle;
    public ImageView mStoryImg;
    public Story story;

    public ItemViewHolder(View view) {
      super(view);
      mStoryTitle = (TextView) view.findViewById(R.id.story_title);
      mStoryImg = (ImageView) view.findViewById(R.id.story_img);
    }
  }

  public class BannerViewHolder extends RecyclerView.ViewHolder {

    private ConvenientBanner<TopStory> banner;

    public BannerViewHolder(View view) {
      super(view);
      banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
    }
  }

  private class FooterViewHolder extends RecyclerView.ViewHolder {
    public FooterViewHolder(View view) {
      super(view);
    }
  }
}

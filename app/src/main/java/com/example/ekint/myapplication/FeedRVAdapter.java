package com.example.ekint.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ekint on 8/5/2018.
 */

public class FeedRVAdapter extends RecyclerView.Adapter<FeedRVAdapter.FeedViewHolder>{
    public void setmFeedList(List<FeedEntry> mFeedList) {
        this.mFeedList = mFeedList;
    }

    private List<FeedEntry> mFeedList;
    private Context mContext;

    public List<FeedEntry> getmFeedList() {
        return mFeedList;
    }

    public FeedRVAdapter(Context context, List<FeedEntry> entryList) {
        this.mContext = context;
        this.mFeedList = entryList;
    }

    @Override
    public FeedRVAdapter.FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_feed, parent, false);
        return new FeedRVAdapter.FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedRVAdapter.FeedViewHolder holder, int position) {

        if((mFeedList == null) || (mFeedList.size() == 0)){
            holder.title.setText(R.string.empty_entry);
            holder.creator.setText(R.string.empty_entry);
            holder.date.setText(R.string.empty_entry);
            holder.description.setText(R.string.empty_entry);
        } else {
            FeedEntry feedItem = mFeedList.get(position);
            holder.title.setText(feedItem.getTitle());
            holder.creator.setText(feedItem.getCreator());
            holder.date.setText(feedItem.getDate());
            holder.description.setText(feedItem.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return ((mFeedList != null) && (mFeedList.size() !=0) ? mFeedList.size() : 1);
    }

    public FeedEntry getEntry(int position) {
        return ((mFeedList != null) && (mFeedList.size() !=0) ? mFeedList.get(position) : null);
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView creator;
        TextView date;
        TextView description;
        public FeedViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.tvFeedTitle);
            this.creator = (TextView) itemView.findViewById(R.id.tvFeedCreator);
            this.date = (TextView) itemView.findViewById(R.id.tvFeedDate);
            this.description = (TextView) itemView.findViewById(R.id.tvFeedDescription);

        }
    }

}

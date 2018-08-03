package com.example.ekint.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ekint on 8/3/2018.
 */

public class EntryMainRVAdapter extends RecyclerView.Adapter<EntryMainRVAdapter.EntryMainViewHolder> {

    private List<Entry> mEntryList;
    private Context mContext;

    public EntryMainRVAdapter(Context context, List<Entry> entryList) {
        this.mContext = context;
        this.mEntryList = entryList;
    }

    @Override
    public EntryMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_entry, parent, false);
        return new EntryMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryMainViewHolder holder, int position) {

        if((mEntryList == null) || (mEntryList.size() == 0)){
            holder.entryImage.setImageResource(R.drawable.baseline_image_black_48dp);
            holder.title.setText(R.string.empty_entry);
        } else {
            Entry entryItem = mEntryList.get(position);
            holder.entryImage.setImageResource(R.drawable.baseline_image_black_48dp);
            holder.title.setText(entryItem.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return ((mEntryList != null) && (mEntryList.size() !=0) ? mEntryList.size() : 1);
    }

    static class EntryMainViewHolder extends RecyclerView.ViewHolder{
        ImageView entryImage;
        TextView title;
        public EntryMainViewHolder(View itemView) {
            super(itemView);
            this.entryImage = (ImageView) itemView.findViewById(R.id.ivEntry);
            this.title = (TextView) itemView.findViewById(R.id.tvTitle);

        }
    }
}

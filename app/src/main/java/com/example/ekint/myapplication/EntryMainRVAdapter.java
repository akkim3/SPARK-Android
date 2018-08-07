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
 * EntryMainRVAdapter is a custom Recycler View adapter responsible
 * for the reusability of card views in Recent Entries RV in MainFragment
 * @author ekint
 * @version 1.0
 * @see MainFragment
 */

public class EntryMainRVAdapter extends RecyclerView.Adapter<EntryMainRVAdapter.EntryMainViewHolder> {

    public void setmEntryList(List<Entry> mEntryList) {
        this.mEntryList = mEntryList;
    }

    private List<Entry> mEntryList;
    private Context mContext;

    public List<Entry> getmEntryList() {
        return mEntryList;
    }

    /**
     * Constructor for custom Recycler View Adapter for main screen
     * @param context   context of Recycler View use
     * @param entryList list containing user submitted, recent entries from database
     */
    public EntryMainRVAdapter(Context context, List<Entry> entryList) {
        this.mContext = context;
        this.mEntryList = entryList;
    }

    /**
     * Called whenever the adapter needs to create a new view in the Recycler View
     * @param parent
     * @param viewType
     * @return         a view holder that contains specific view type in Recycler View
     */
    @Override
    public EntryMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_entry, parent, false);
        return new EntryMainViewHolder(view);
    }

    /**
     * Called by the layout manager when it wants new data in an existing row
     * @param holder
     * @param position position of view in recycler view
     */
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

    public Entry getEntry(int position) {
        return ((mEntryList != null) && (mEntryList.size() !=0) ? mEntryList.get(position) : null);
    }

    /**
     * Container for views that populate Recycler View
     */
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

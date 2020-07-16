package com.primalmatrics.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.primalmatrics.R;
import com.primalmatrics.model.Feed;
import com.primalmatrics.utils.OnItemClickListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder> {
    OnItemClickListener mItemClickListener;
    private Activity activity;
    private ArrayList<Feed> reflections = new ArrayList<Feed>();

    public FeedListAdapter(Activity activity, ArrayList<Feed> reflections) {
        this.activity = activity;
        this.reflections = reflections;
    }
    
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        final LayoutInflater mInflater = LayoutInflater.from (parent.getContext ());
        final View sView = mInflater.inflate (R.layout.list_item_feed, parent, false);
        return new ViewHolder (sView);
    }
    
    @Override
    public void onBindViewHolder (final ViewHolder holder, int position) {
        final Feed reflection = reflections.get (position);


            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            try {
                Date mDate = sdf.parse (reflection.getDate ());
                long time = mDate.getTime ();
            //    holder.tvTime.setText (TimeAgo.using (time));
            } catch (ParseException e) {
                e.printStackTrace ();
            }


    }
    
    @Override
    public int getItemCount () {
        return reflections.size ();
       // return r;
    }
    
    public void SetOnItemClickListener (final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder (View view) {
            super (view);
            view.setOnClickListener (this);
        }
        
        @Override
        public void onClick (View v) {
        }
    }
}
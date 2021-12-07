package edu.uga.cs.ridewithme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String DEBUG_TAG = "RecyclerViewAdapter";

    private ArrayList<String> postTitles = new ArrayList<>();
    private Context context;


    public RecyclerViewAdapter(ArrayList<String> postTitles, Context context) {
        this.postTitles = postTitles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_items,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(DEBUG_TAG, "onBindViewHolder: listing");
        holder.posts.setText(postTitles.get(position));


    }

    @Override
    public int getItemCount() {
        return postTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView posts;
        private LinearLayout posts_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posts = itemView.findViewById(R.id.samples);
            posts_layout = itemView.findViewById(R.id.posts_layout);

        }
    }
}

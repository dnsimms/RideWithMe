package edu.uga.cs.ridewithme;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PastRidesRecycleView extends RecyclerView.Adapter<PastRidesRecycleView.ViewHolder>{

    private static final String DEBUG_TAG = "PastRidesRecycleView";

    private ArrayList<String> pastTitles = new ArrayList<>();
    private Context context;



    public PastRidesRecycleView(ArrayList<String> postTitles, Context context) {
        this.pastTitles = postTitles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.past_items,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(DEBUG_TAG, "onBindViewHolder: listing");
        holder.posts.setText(pastTitles.get(position));

        holder.posts_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent;
                intent = new Intent(context, Past_Rides.class); //TODO change past_rides to past_details
                int pos = holder.getAdapterPosition();
                intent.putExtra("position", pos);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return pastTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView posts;
        private LinearLayout posts_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posts = itemView.findViewById(R.id.pastItems);
            posts_layout = itemView.findViewById(R.id.past_layout);

        }
    }
}

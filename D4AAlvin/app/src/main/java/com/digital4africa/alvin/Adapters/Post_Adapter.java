package com.digital4africa.alvin.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.digital4africa.alvin.Fetchers.Post_Fetcher;
import com.digital4africa.alvin.R;

import java.util.ArrayList;

public class Post_Adapter extends RecyclerView.Adapter<Post_Adapter.ViewHolder> {

    private Context context;

    private ArrayList<Post_Fetcher> post_fetchers;

    public Post_Adapter(Context context, ArrayList<Post_Fetcher> post_fetchers) {
        this.context = context;
        this.post_fetchers = post_fetchers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.post_layout,viewGroup,false);

        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.post_id.setText(post_fetchers.get(i).getPostid());

        viewHolder.post_title.setText(post_fetchers.get(i).getPost_title());

    }

    @Override
    public int getItemCount() {
        return post_fetchers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView post_id,post_title;

        private ImageView post_banner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            post_id=(TextView)itemView.findViewById(R.id.post_id);

            post_title=(TextView)itemView.findViewById(R.id.post_title);

            post_banner=(ImageView)itemView.findViewById(R.id.post_banner);




        }
    }
}

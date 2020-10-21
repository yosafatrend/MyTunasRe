package com.spect.mytunas.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.spect.mytunas.R;
import com.spect.mytunas.Utils;
import com.spect.mytunas.activity.NewsDetailActivity;
import com.spect.mytunas.models.Article;

import java.util.List;

public class NewsHomeAdapter extends RecyclerView.Adapter<NewsHomeAdapter.MyViewHolder> {
    private List<Article> articles;
    private Context context;

    public NewsHomeAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, final int position) {
        final MyViewHolder holder = holders;
        final Article model = articles.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(model.getUrlToImage())
                .centerCrop()
                .into(holder.imageView);
        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.itemNewsHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);

                intent.putExtra("url", model.getUrl());
                intent.putExtra("title", model.getTitle());
                intent.putExtra("img", model.getUrlToImage());
                intent.putExtra("date", model.getPublishedAt());
                intent.putExtra("source", model.getSource().getName());
                intent.putExtra("author", model.getAuthor());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, desc;
        ImageView imageView;
        ConstraintLayout itemNewsHome;
        ProgressBar progressbar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleNewsHome);
            desc = itemView.findViewById(R.id.descNewsHome);
            imageView = itemView.findViewById(R.id.imgNewsHome);
            itemNewsHome = itemView.findViewById(R.id.item_news_home);

        }
    }
}

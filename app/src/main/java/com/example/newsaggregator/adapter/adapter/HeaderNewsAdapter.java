package com.example.newsaggregator.adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaggregator.R;
import com.example.newsaggregator.model.model.Articles;
import com.example.newsaggregator.model.model.NewsModel;
import com.example.newsaggregator.ui.activity.MainActivity;
import com.example.newsaggregator.ui.fragments.OnNavigationListener;
import com.example.newsaggregator.ui.fragments.fragments.DetailNewsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeaderNewsAdapter extends RecyclerView.Adapter<HeaderNewsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Articles> states;

    private OnNavigationListener onNavigationListener;

    public HeaderNewsAdapter(Context context, List<Articles> states){
        onNavigationListener = (MainActivity) context;

        this.inflater = LayoutInflater.from(context);
        this.states = states;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles article = states.get(position);

        Picasso.with(inflater.getContext()).load(article.getUrlToImage()).into(holder.headerItemImage);
        holder.headerItemTitle.setText(article.getTitle());
        holder.headerItemDescription.setText(article.getDescription());
        holder.headerItemAuthor.setText(article.getAuthor());
        holder.headerItemPublished.setText(article.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView headerItemImage;
        private TextView headerItemTitle, headerItemDescription,
                headerItemAuthor, headerItemPublished;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headerItemImage = (ImageView) itemView.findViewById(R.id.header_item_image);
            headerItemTitle = (TextView) itemView.findViewById(R.id.header_item_title);
            headerItemDescription = (TextView) itemView.findViewById(R.id.header_item_description);
            headerItemAuthor = (TextView) itemView.findViewById(R.id.header_item_author);
            headerItemPublished = (TextView) itemView.findViewById(R.id.header_item_published);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationListener.onNavigate(new DetailNewsFragment(), true);
                }
            });
        }
    }
}

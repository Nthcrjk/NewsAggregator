package com.example.newsaggregator.adapter.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaggregator.R;
import com.example.newsaggregator.helpers.TextChanger;
import com.example.newsaggregator.model.model.Articles;
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

    private String fixText(String text){
        text = Html.fromHtml(text).toString();
        text = text.replace("[\\s]+", " ");
        return text;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles article = states.get(position);

        if (article.getContent() != null)
            article.setContent(TextChanger.fixContent(article.getContent()));

        if (article.getTitle() != null)
            article.setTitle(TextChanger.fixText(article.getTitle()));
        if (article.getDescription() != null)
            article.setDescription(TextChanger.fixText(article.getDescription()));
        if (article.getContent() != null)
            article.setContent(TextChanger.fixText(article.getContent()));

        Picasso.with(inflater.getContext()).load(article.getUrlToImage()).into(holder.headerItemImage);
        holder.headerItemTitle.setText(article.getTitle());
        holder.headerItemDescription.setText(article.getDescription());

        if (article.getAuthor() == null){
            holder.headerItemAuthorWord1.setText("");
        } else{
            holder.headerItemAuthorWord2.setText(article.getAuthor());
        }

        holder.headerItemPublished.setText(article.getPublishedAt());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationListener.onNavigate(new DetailNewsFragment(article), true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView headerItemImage;
        private TextView headerItemTitle, headerItemDescription,
                headerItemAuthorWord2, headerItemAuthorWord1, headerItemPublished;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headerItemImage = (ImageView) itemView.findViewById(R.id.header_item_image);
            headerItemTitle = (TextView) itemView.findViewById(R.id.header_item_title);
            headerItemDescription = (TextView) itemView.findViewById(R.id.header_item_description);
            headerItemAuthorWord2 = (TextView) itemView.findViewById(R.id.header_item_author_word2);
            headerItemAuthorWord1 = (TextView) itemView.findViewById(R.id.header_item_author_word1);
            headerItemPublished = (TextView) itemView.findViewById(R.id.header_item_published);
        }
    }
}

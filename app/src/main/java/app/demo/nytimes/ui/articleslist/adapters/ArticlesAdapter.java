package app.demo.nytimes.ui.articleslist.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.demo.nytimes.ui.articledetails.ArticleDetailActivity;
import app.demo.nytimes.ui.articledetails.ArticleDetailFragment;
import app.demo.nytimes.R;
import app.demo.nytimes.engine.entities.Article;
import app.demo.nytimes.ui.articleslist.ArticlesListActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Beshoy Adel on 04/19/2017.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Article> mArticlesList;
    private ArticlesListActivity mActivity;
    private boolean mTwoPane;

    public ArticlesAdapter(ArticlesListActivity activity, ArrayList<Article> articlesList, boolean twoPane) {
        this.mActivity = activity;
        this.mArticlesList = articlesList;
        mTwoPane = twoPane;
    }

    @Override
    public int getItemCount() {
        return mArticlesList == null ? 0 : mArticlesList.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_article, parent, false);
        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
        final Article mArticle = mArticlesList.get(position);

        articleViewHolder.mTitleTextView.setText(mArticle.getTitle());
        articleViewHolder.mAuthorTextView.setText(mArticle.getByline());

        if (mArticle.getMedia() != null && mArticle.getMedia().size() > 0 &&
                mArticle.getMedia().get(0).getMetaData()!=null && mArticle.getMedia().get(0).getMetaData().size()>0)
        {
            Picasso.get().load(mArticle.getMedia().get(0).getMetaData().get(0).getUrl()).into(articleViewHolder.mMediaImageView);
        }

        articleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ArticleDetailFragment.ARG_ARTICLE, new Gson().toJson(mArticle));
                    ArticleDetailFragment fragment = new ArticleDetailFragment();
                    fragment.setArguments(arguments);
                    mActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ArticleDetailActivity.class);
                    intent.putExtra(ArticleDetailFragment.ARG_ARTICLE, new Gson().toJson(mArticle));
                    context.startActivity(intent);
                }
            }
        });
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_media_image)
        ImageView mMediaImageView;

        @BindView(R.id.tv_title)
        TextView mTitleTextView;

        @BindView(R.id.tv_author)
        TextView mAuthorTextView;

        private ArticleViewHolder(View convertView) {
            super(convertView);
            ButterKnife.bind(this, itemView);
        }
    }
}


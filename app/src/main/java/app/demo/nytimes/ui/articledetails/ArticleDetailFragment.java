package app.demo.nytimes.ui.articledetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import app.demo.nytimes.R;
import app.demo.nytimes.engine.entities.Article;
import app.demo.nytimes.ui.articleslist.ArticlesListActivity;

public class ArticleDetailFragment extends Fragment {

    public static final String ARG_ARTICLE = "article";
    private Article mArticle;

    public ArticleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null && getArguments().containsKey(ARG_ARTICLE)) {
            mArticle = new Gson().fromJson(getArguments().getString(ARG_ARTICLE),Article.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_details, container, false);

        if (mArticle != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mArticle.getTitle());
        }

        return rootView;
    }
}

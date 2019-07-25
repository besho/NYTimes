package app.demo.nytimes.ui.articleslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import app.demo.nytimes.R;
import app.demo.nytimes.engine.entities.Article;
import app.demo.nytimes.ui.articleslist.adapters.ArticlesAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;

public class ArticlesListActivity extends AppCompatActivity implements ArticleView, View.OnClickListener {

    private boolean mTwoPane;

    @BindView(R.id.rv_articles)
    RecyclerView mArticlesRecyclerView;

    private ArrayList<Article> mArticlesList = new ArrayList<>();
    private ArticlesAdapter mArticlesAdapter;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.v_reload)
    View mReloadView;

    @BindView(R.id.btn_reload)
    Button mReloadButton;

    private ArticlesPresenter mArticlesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        mArticlesPresenter = new ArticlesPresenter(this);
        setupView();
    }

    @Override
    public void setupView() {

        mReloadButton.setOnClickListener(this);

        mArticlesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mArticlesAdapter = new ArticlesAdapter(this,mArticlesList,mTwoPane);
        mArticlesRecyclerView.setAdapter(mArticlesAdapter);

        mArticlesPresenter.getArticles();
    }

    @Override
    public void showLoadingProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindArticlesList(ArrayList<Article> articlesList) {
        mArticlesList.addAll(articlesList);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showReloadView() {
        mReloadView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideReloadView() {
        mReloadView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_reload) {
            hideReloadView();
            mArticlesPresenter.getArticles();
        }
    }
}
